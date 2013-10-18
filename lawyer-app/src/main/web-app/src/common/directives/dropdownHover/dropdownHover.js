angular.module('lawyer.dropdown.hover', [])

    .directive('dropdownHover',  ['$document', '$location', function ($document, $location) {
        var openElement = null,
            closeMenu   = angular.noop;
        return {
            restrict : 'CA',
            replace : true,
            link: function(scope, element, attrs) {
                scope.$watch('$location.path', function() { closeMenu(); });
                element.parent().bind('click', function() { closeMenu(); });
                element.bind('mouseout', closeMenu);
                element.bind('mouseover', function (event) {
                    console.log('mouseover');

                    var elementWasOpen = (element === openElement);

                    event.preventDefault();
                    event.stopPropagation();


                    if (!elementWasOpen && !element.hasClass('disabled') && !element.prop('disabled')) {
                        console.log('if');
                        element.parent().addClass('open');
                        openElement = element;
                        closeMenu = function (event) {
                            console.log('closeMenu');
                            if (event) {
                                event.preventDefault();
                                event.stopPropagation();
                            }
                            $document.unbind('mouseover', closeMenu);
                            element.parent().removeClass('open');
                            closeMenu = angular.noop;
                            openElement = null;
                        };
                        $document.bind('click', closeMenu);
                        element.parent().bind('mouseout', function () {
                            console.log('teste mouseout');
                            console.log(element);
                            console.log(element.childNodes);
                        });
                        console.log('click');
                    }
                });
            }
        };
    }])
;

