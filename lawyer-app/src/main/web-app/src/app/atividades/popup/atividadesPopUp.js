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

    .controller('AtividadesPopUp', ['$scope', '$modalInstance', '$log', function ($scope, $modalInstance, $log) {

        $scope.close = function () {
            $modalInstance.close();
        };

    }])
;

