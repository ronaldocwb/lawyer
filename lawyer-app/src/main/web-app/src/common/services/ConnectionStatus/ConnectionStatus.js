angular.module('ConnectionStatus', [])

/**
 * Verifica se a conexão musa de status.
 * Caso mudar, temos que implementar uma solução que avise o usuário para ele não navegar offline sem preenchimento de dados e sem saber o que ocorreu.
 * É igual ao Gmail.
 */
.factory('ConnectionStatus', ['$rootScope', '$window', function ($rootScope, $window) {

    var _online = true;

    return {
        handle : function() {
            _online = navigator.onLine;
            $window.addEventListener("offline", function () {
                $rootScope.$broadcast('ConnectionStatus.CHANGE', _online);
                _online = false;
            }, false);
            $window.addEventListener("online", function () {
                $rootScope.$broadcast('ConnectionStatus.CHANGE', _online);
                _online = true;
            }, false);
        },

        get : function () {
            return _online;
        }
    };
}])
;

