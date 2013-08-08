angular.module('lawyer.connectionStatus', [])

/**
 * Verifica se a conexao muda de status.
 * Caso mudar, temos que implementar uma solucao que avise o usuario para ele na1o navegar
 * offline (e vai navegar!!!) sem preenchimento de dados que vem do server e sem saber o que ocorreu.
 * Eh igual ao Gmail.
 */
    .factory('connectionStatus', ['$rootScope', '$window', '$timeout', function ($rootScope, $window, $timeout) {

        function emit(_online) {
            $rootScope.$broadcast('ConnectionStatus.CHANGE', _online);
        }

        return {
            handle: function () {
                $timeout(function () {
                    if (!navigator.onLine) {
                        if (!$rootScope.$$phase) {
                            emit(false);
                        }
                    }
                }, 500);

                $window.addEventListener("offline", function () {
                    emit(false);
                }, false);

                $window.addEventListener("online", function () {
                    emit(true);
                }, false);
            }
        };

    }])
;

