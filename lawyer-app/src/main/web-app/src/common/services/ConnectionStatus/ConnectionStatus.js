angular.module('ConnectionStatus', [])

/**
 * Verifica se a conexao muda de status.
 * Caso mudar, temos que implementar uma solucao que avise o usuario para ele na1o navegar
 * offline (e vai navegar!!!) sem preenchimento de dados que vem do server e sem saber o que ocorreu.
 * Eh igual ao Gmail.
 */
.factory('ConnectionStatus', ['$rootScope', '$window', function ($rootScope, $window) {

    var _online = true;

    return {
        handle : function() {
            _online = navigator.onLine;
            $window.addEventListener("offline", function () {
                _online = false;
                $rootScope.$broadcast('ConnectionStatus.CHANGE', _online);
            }, false);
            $window.addEventListener("online", function () {
                _online = true;
                $rootScope.$broadcast('ConnectionStatus.CHANGE', _online);
            }, false);
        },

        get : function () {
            return _online;
        }
    };
}])
;

