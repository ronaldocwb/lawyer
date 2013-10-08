angular.module('lawyer.menu.central', [
    ])


    .directive('menuCentral', [function () {
        return {
            restrict: 'E',
            replace: true,
            controller: 'MenuCentralController',
            templateUrl: 'directives/menus/central/central.tpl.html',
            link: function (scope, element, attrs) {
            }
        };
    }])

    .controller('MenuCentralController', ['$scope', '$location', function ($scope, $location) {
        $scope.ativo = function (url) {
            return $location.path().indexOf(url) > -1 ? 'active' : '';
        };
    }])

;

