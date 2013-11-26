/**
 * @ngdoc object
  * @name lawyer.auth
 * @description
 * Módulo de gerenciamento de credenciais
 *
 */
angular.module('auth', [
        'ngCookies'
    ])
    .factory('auth', ['$cookieStore', function ($cookieStore) {

        var _user = {};

        return {

            /**
             * @ngdoc function
             * @name lawyer.auth:user
             * @description
             * Recupera a instância em memória do usuário.
             *
             */
            user : _user,

            /**
             * @ngdoc function
             * @name lawyer.auth:set
             * @description
             * Cria o usuário com base no cookie ou recupera via serviço REST
             *
             */
            set: function () {
                _user = $cookieStore.get('lawyer.user') || {email : '', token : '', authorities : []};
                $cookieStore.put('lawyer.user', _user);
            },

            /**
             * @ngdoc function
             * @name lawyer.auth:authorize
             * @description
             * Autoriza a apresentação de um elemento baseado nas permissões atuais
             *
             */
            authorize: function (accessLevel) {
                var _authorized = false;
                angular.forEach(_user.authorities, function (value) {
                    if (value.authority && value.authority === accessLevel) {
                        _authorized = true;
                    }
                });
                return _authorized;
            }
        };
    }])
;
