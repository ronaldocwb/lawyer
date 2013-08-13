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

    .controller('MenuDireitaController', ['$scope', '$dialog', '$log', function ($scope, $dialog, $log) {
        $scope.openAtividades = function () {
            var dialog = $dialog.dialog({
                backdropFade: true,
                dialogFade: true,
                controller: 'AtividadesPopUp'
            });
            dialog.open('atividades/popup/atividadesPopUp.tpl.html').then(function (result) {
                $log.debug('Retorno do modal: ', result);
            });
        };
    }])
;

