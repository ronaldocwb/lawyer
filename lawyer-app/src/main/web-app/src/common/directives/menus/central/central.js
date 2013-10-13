angular.module('lawyer.menu.central', [
    ])

    .directive('menuCentral', ['$location', function ($location) {
        return {
            restrict: 'E',
            replace: true,
            templateUrl: 'directives/menus/central/central.tpl.html',
            link: function (scope, elem, attrs) {
                scope.ativo = function (url) {
                    return $location.path().indexOf(url) > -1 ? 'active' : '';
                };
            }
        };
    }])
;

