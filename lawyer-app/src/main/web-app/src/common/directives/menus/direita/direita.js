angular.module('lawyer.menu.direita', [])

    .directive('menuDireita', [function () {
        return {
            restrict : 'E',
            replace : true,
            controller : 'MenuDireitaController',
            templateUrl : 'directives/menus/direita/direita.tpl.html',
            link : function (scope, element, attrs) {
            }
        };
    }])

    .controller('MenuDireitaController', ['$scope', '$modal', '$log', function ($scope, $modal, $log) {
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

