angular.module('modal-401', []).

    directive('modal401', [function () {
        return {
            restrict: 'C',
            replace: true,
            templateUrl: 'directives/modal-401/modal-401.tpl.html',
            link : function (scope, element, attrs) {
                scope.$on('open-modal-401', function () {

                });
            }
        };
    }])
;
