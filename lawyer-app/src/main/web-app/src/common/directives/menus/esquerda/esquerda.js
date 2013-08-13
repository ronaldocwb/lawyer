angular.module('lawyer.menu.esquerda', ['timer', 'lawyer.atividades.popup'])

    .directive('menuEsquerda', [function () {
        return {
            restrict: 'E',
            replace: true,
            controller: 'MenuEsquerdaController',
            templateUrl: 'directives/menus/esquerda/esquerda.tpl.html',
            link: function (scope, element, attrs) {
            }
        };
    }])

    .controller('MenuEsquerdaController', ['$scope', '$dialog', '$log', function ($scope, $dialog, $log) {


    }])
;
