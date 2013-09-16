/**
 * @ngdoc object
 * @name lawyer.atividades.popup
 * @description
 * Pop-up para administração das atividades cadastradas pelo usuário.
 *
 * O usuário poderá alterar atividades passadas e iniciar / pausar novas atividades.
 * A atividade atual é carregada automaticamente pela directive {@link lawyer.menu.direita}
 */
angular.module('lawyer.atividades.popup', [])

    .config([function () {

    }])

    .controller('AtividadesPopUp', ['$scope', 'dialog', '$log', function ($scope, dialog, $log) {

        $scope.close = function () {
            dialog.close();
        };

    }])
;

