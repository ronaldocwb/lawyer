angular.module('lawyer.atividades.popup', [])

    .config([function () {

    }])

    .controller('AtividadesPopUp', ['$scope', 'dialog', '$log', function ($scope, dialog, $log) {

        $scope.close = function () {
            dialog.close();
        };

    }])
;

