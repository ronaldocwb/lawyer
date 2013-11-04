angular.module('lawyer.typeahead', ['ui.bootstrap.position', 'ui.bootstrap.bindHtml'])

/**
 * A helper service that can parse typeahead's syntax (string provided by users)
 * Extracted to a separate service for ease of unit testing
 */
    .factory('typeaheadParserLawyer', ['$parse', function ($parse) {

        //                      00000111000000000000022200000000000000003333333333333330000000000044000
        var TYPEAHEAD_REGEXP = /^\s*(.*?)(?:\s+as\s+(.*?))?\s+for\s+(?:([\$\w][\$\w\d]*))\s+in\s+(.*)$/;

        return {
            parse:function (input) {
                var match = input.match(TYPEAHEAD_REGEXP), modelMapper, viewMapper, source;
                if (!match) {
                    throw new Error(
                        "Expected typeahead specification in form of '_modelValue_ (as _label_)? for _item_ in _collection_'" +
                            " but got '" + input + "'.");
                }

                return {
                    itemName:match[3],
                    source:$parse(match[4]),
                    viewMapper:$parse(match[2] || match[1]),
                    modelMapper:$parse(match[1])
                };
            }
        };
    }])

    .directive('typeaheadLawyer', ['$compile', '$parse', '$q', '$timeout', '$document', '$position', 'typeaheadParserLawyer',
        function ($compile, $parse, $q, $timeout, $document, $position, typeaheadParserLawyer) {

            var HOT_KEYS = [9, 13, 27, 38, 40];

            return {
                require:'ngModel',
                link:function (originalScope, element, attrs, modelCtrl) {

                    //SUPPORTED ATTRIBUTES (OPTIONS)
                    originalScope.valor = {
                        text : null,
                        used : false
                    };
                    //minimal no of characters that needs to be entered before typeahead kicks-in
                    var minSearch = originalScope.$eval(attrs.typeaheadMinLength) || 1;

                    //minimal wait time after last character typed before typehead kicks-in
                    var waitTime = originalScope.$eval(attrs.typeaheadWaitMs) || 0;

                    //should it restrict model values to the ones selected from the popup only?
                    var isEditable = originalScope.$eval(attrs.typeaheadEditable) !== false;

                    //binding to a variable that indicates if matches are being retrieved asynchronously
                    var isLoadingSetter = $parse(attrs.typeaheadLoading).assign || angular.noop;

                    //a callback executed when a match is selected
                    var onSelectCallback = $parse(attrs.typeaheadOnSelect);

                    var onAddCallback = $parse(attrs.typeaheadOnAdd);

                    var inputFormatter = attrs.typeaheadInputFormatter ? $parse(attrs.typeaheadInputFormatter) : undefined;

                    //INTERNAL VARIABLES

                    //model setter executed upon match selection
                    var $setModelValue = $parse(attrs.ngModel).assign;

                    //expressions used by typeahead
                    var parserResult = typeaheadParserLawyer.parse(attrs.typeaheadLawyer);

                    

                    //pop-up element used to display matches
                    var popUpEl = angular.element('<typeahead-popup-lawyer></typeahead-popup-lawyer>');
                    popUpEl.attr({
                        matches: 'matches',
                        active: 'activeIdx',
                        select: 'select(activeIdx)',
                        query: 'query',
                        position: 'position',
                        valor: 'valor',
                        add: 'add()',
                        fokus : 'fokus'
                    });
                    //custom item template
                    if (angular.isDefined(attrs.typeaheadTemplateUrl)) {
                        popUpEl.attr('template-url', attrs.typeaheadTemplateUrl);
                    }

                    //create a child scope for the typeahead directive so we are not polluting original scope
                    //with typeahead-specific data (matches, query etc.)
                    var scope = originalScope.$new();
                    originalScope.$on('$destroy', function(){
                        scope.$destroy();
                    });

                    scope.fokus = {
                        hasFocus : false,
                        id : null
                    };
                    scope.fokus.id = scope.$id;

                    var resetMatches = function() {
                        scope.matches = [];
                        scope.activeIdx = -1;
                    };

                    var getMatchesAsync = function(inputValue) {
                        var locals = {$viewValue: inputValue};
                        isLoadingSetter(originalScope, true);
                        $q.when(parserResult.source(scope, locals)).then(function(matches) {
                            //it might happen that several async queries were in progress if a user were typing fast
                            //but we are interested only in responses that correspond to the current view value
                            if (inputValue === modelCtrl.$viewValue && scope.fokus.hasFocus) {
                                if (matches.length > 0) {

                                    scope.activeIdx = 0;
                                    scope.matches.length = 0;

                                    //transform labels
                                    for(var i=0; i<matches.length; i++) {
                                        locals[parserResult.itemName] = matches[i];
                                        scope.matches.push({
                                            label: parserResult.viewMapper(scope, locals),
                                            model: matches[i]
                                        });
                                    }

                                    scope.query = inputValue;
                                    //position pop-up with matches - we need to re-calculate its position each time we are opening a window
                                    //with matches as a pop-up might be absolute-positioned and position of an input might have changed on a page
                                    //due to other elements being rendered
                                } else {
                                    resetMatches();
                                }
                                scope.position = $position.position(element);
                                scope.position.top = scope.position.top + element.prop('offsetHeight');
                                isLoadingSetter(originalScope, false);
                            }
                        }, function(){
                            resetMatches();
                            isLoadingSetter(originalScope, false);
                        });
                    };

                    resetMatches();

                    //we need to propagate user's query so we can higlight matches
                    scope.query = undefined;

                    //Declare the timeout promise var outside the function scope so that stacked calls can be cancelled later
                    var timeoutPromise;

                    //plug into $parsers pipeline to open a typeahead on view changes initiated from DOM
                    //$parsers kick-in on all the changes coming from the view as well as manually triggered by $setViewValue
                    modelCtrl.$parsers.unshift(function (inputValue) {
                        originalScope.valor.text = modelCtrl.$viewValue;
                        originalScope.valor.used = false;
                        scope.fokus.hasFocus = true;

                        if (inputValue && inputValue.length >= minSearch) {
                            if (waitTime > 0) {
                                if (timeoutPromise) {
                                    $timeout.cancel(timeoutPromise);//cancel previous timeout
                                }
                                timeoutPromise = $timeout(function () {
                                    getMatchesAsync(inputValue);
                                }, waitTime);
                            } else {
                                getMatchesAsync(inputValue);
                            }
                        } else {
                            isLoadingSetter(originalScope, false);
                            resetMatches();
                        }

                        if (isEditable) {
                            return inputValue;
                        } else {
                            if (!inputValue) {
                                // Reset in case user had typed something previously.
                                modelCtrl.$setValidity('editable', true);
                                return inputValue;
                            } else {
                                modelCtrl.$setValidity('editable', false);
                                return undefined;
                            }
                        }
                    });

                    modelCtrl.$formatters.push(function (modelValue) {

                        var candidateViewValue, emptyViewValue;
                        var locals = {};

                        if (inputFormatter) {

                            locals['$model'] = modelValue;
                            return inputFormatter(originalScope, locals);

                        } else {

                            //it might happen that we don't have enough info to properly render input value
                            //we need to check for this situation and simply return model value if we can't apply custom formatting
                            locals[parserResult.itemName] = modelValue;
                            candidateViewValue = parserResult.viewMapper(originalScope, locals);
                            locals[parserResult.itemName] = undefined;
                            emptyViewValue = parserResult.viewMapper(originalScope, locals);

                            return candidateViewValue!== emptyViewValue ? candidateViewValue : modelValue;
                        }
                    });

                    scope.select = function (activeIdx) {
                        //called from within the $digest() cycle
                        var locals = {};
                        var model, item;

                        originalScope.valor.used = true;

                        locals[parserResult.itemName] = item = scope.matches[activeIdx].model;
                        model = parserResult.modelMapper(originalScope, locals);
                        $setModelValue(originalScope, model);
                        modelCtrl.$setValidity('editable', true);

                        onSelectCallback(originalScope, {
                            $item: item,
                            $model: model,
                            $label: parserResult.viewMapper(originalScope, locals)
                        });
                        resetMatches();

                        //return focus to the input element if a mach was selected via a mouse click event
                        element[0].focus();
                    };

                    scope.add = function () {
                        onAddCallback(originalScope, {
                            $item: modelCtrl.$viewValue
                        });
                        modelCtrl.$setValidity('editable', true);

                        resetMatches();

                        //return focus to the input element if a mach was selected via a mouse click event
                        element[0].focus();
                    };



                    //bind keyboard events: arrows up(38) / down(40), enter(13) and tab(9), esc(27)
                    element.bind('keydown', function (evt) {
                        //typeahead is open and an "interesting" key was pressed
                        originalScope.valor.used = false;
                        originalScope.valor.text = modelCtrl.$viewValue;

                        if (scope.matches.length === 0 || HOT_KEYS.indexOf(evt.which) === -1) {
                            return;
                        }

                        evt.preventDefault();

                        if (evt.which === 40) {
                            scope.activeIdx = (scope.activeIdx + 1) % scope.matches.length;
                            scope.$digest();

                        } else if (evt.which === 38) {
                            scope.activeIdx = (scope.activeIdx ? scope.activeIdx : scope.matches.length) - 1;
                            scope.$digest();

                        } else if (evt.which === 13 || evt.which === 9) {
                            scope.$apply(function () {
                                scope.select(scope.activeIdx);
                            });

                        } else if (evt.which === 27) {
                            evt.stopPropagation();

                            resetMatches();
                            scope.$digest();
                        }
                    });

                    element.bind('blur', function (evt) {
                        scope.fokus.hasFocus = false;
                    });

                    // Keep reference to click handler to unbind it.
                    var dismissClickHandler = function (evt) {
                        if (element[0] !== evt.target) {
                            resetMatches();
                            scope.$digest();
                        }
                    };

                    $document.bind('click', dismissClickHandler);

                    originalScope.$on('$destroy', function(){
                        $document.unbind('click', dismissClickHandler);
                    });

                    element.after($compile(popUpEl)(scope));
                }
            };

        }])

    .directive('typeaheadPopupLawyer', function () {
        return {
            restrict:'E',
            scope:{
                matches:'=',
                query:'=',
                active:'=',
                position:'=',
                select:'&',
                add:'&',
                valor:'=',
                hasAddNewValue : '=',
                fokus : '='
            },
            replace:true,
            templateUrl:'directives/typeaheadLawyer/typeahead-popup.tpl.html',
            link:function (scope, element, attrs) {

                scope.templateUrl = attrs.templateUrl;

                scope.isOpen = function () {
                    return scope.matches.length > 0 || (scope.valor.text !== null && scope.valor.text !== '' && scope.valor.used === false);
                };

                scope.isActive = function (matchIdx){
                    return scope.active == matchIdx;
                };

                scope.selectActive = function (matchIdx) {
                    scope.active = matchIdx;
                };

                scope.selectMatch = function (activeIdx) {
                    scope.select({activeIdx:activeIdx});
                };

                scope.addNew = function () {
                    scope.valor.used = true;
                    scope.add();
                };
                
                scope.showAdd = function () {
                    var show = !scope.valor.used;
                    angular.forEach(scope.matches, function (match) {
                        if (scope.valor.text === match.label) {
                            show = !show;
                        }
                    });
                    return show;
                };

                scope.showList = function () {
                    return scope.fokus.hasFocus;
                };
            }
        };
    })

    .directive('typeaheadMatchLawyer', ['$http', '$templateCache', '$compile', '$parse', function ($http, $templateCache, $compile, $parse) {
        return {
            restrict:'E',
            scope:{
                index:'=',
                match:'=',
                query:'=',
                valor:'='
            },
            link:function (scope, element, attrs) {
                var tplUrl = $parse(attrs.templateUrl)(scope.$parent) || 'directives/typeaheadLawyer/typeahead-match.tpl.html';
                $http.get(tplUrl, {cache: $templateCache}).success(function(tplContent){
                    element.replaceWith($compile(tplContent.trim())(scope));
                });
            }
        };
    }])

    .filter('typeaheadHighlightLawyer', function() {

        function escapeRegexp(queryToEscape) {
            return queryToEscape.replace(/([.?*+^$[\]\\(){}|-])/g, "\\$1");
        }

        return function(matchItem, query) {
            return query ? matchItem.replace(new RegExp(escapeRegexp(query), 'gi'), '<strong>$&</strong>') : matchItem;
        };
    });