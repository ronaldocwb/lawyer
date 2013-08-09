angular.module('lawyer.account.popup', [
    ])

    .controller('AccountPopUp', ['$scope', 'dialog', '$log', function ($scope, dialog, $log) {

        $scope.close = function () {
            $log.debug('Fechou a janela de cadastro.');
            dialog.close();
        };

    }])
;


