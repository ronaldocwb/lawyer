angular.module('Auth', [
        'ngCookies'
    ])
/**
 * Insere um usuário no cookie ou recupera ele.
 */
    .factory('Auth', ['$cookieStore', '$location', '$rootScope', function ($cookieStore, $location, $rootScope) {

        var _user = {};

        return {
            set: function () {
                _user.authorities = $cookieStore.get('lawyer.authorities');
                _user.token = $cookieStore.get('lawyer.token');
                _user.email = $cookieStore.get('lawyer.email');
                console.log(_user);
                $cookieStore.put('lawyer.user', _user);
            },
            get: function () {
                if (typeof _user === 'undefined' || _user === null) {
                    _user = $cookieStore.get('lawyer.user');
                }
                return _user;
            },
            authorize: function (accessLevel, role) {
                if (role === undefined) {
                    // validar a lista de authorities, e nao como um objeto
                    role = _user.authorities;
                }
                return accessLevel & role;
            }

        };
    }])
;
