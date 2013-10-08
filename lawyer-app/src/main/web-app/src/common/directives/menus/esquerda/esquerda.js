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

    .controller('MenuEsquerdaController', ['$scope', '$modal', '$log', function ($scope, $modal, $log) {
        $scope.openAtividades = function () {
            var dialog = $modal.open({
                backdropFade: true,
                dialogFade: true,
                controller: 'AtividadesPopUp',
                templateUrl: 'atividades/popup/atividadesPopUp.tpl.html'
            });
            // dialog.open('atividades/popup/atividadesPopUp.tpl.html').then(function (result) {
            //   $log.debug('Retorno do modal: ', result, 'lol');
            //});
        };
    }])
;
