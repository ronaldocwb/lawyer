angular.module('lawyer.auth', [
        'ngCookies'
    ])
/**
 * Insere um usuário no cookie ou recupera ele.
 * Com base nas permissões, autoriza ou nao um acesso.
 */
    .factory('auth', ['$cookieStore', function ($cookieStore) {

        var _user = {};

        return {

            user : _user,

            set: function () {
                _user = $cookieStore.get('lawyer.user') || {email : '', token : '', authorities : []};
                $cookieStore.put('lawyer.user', _user);
            },

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
