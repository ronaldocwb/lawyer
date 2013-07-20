angular.module('Auth', [
        'ngCookies'
])
    /**
     * Insere um usuário no cookie ou recupera ele.
     */
    .factory('Auth', ['$cookieStore', '$location', '$q', function ($cookieStore, $location, $q) {

        var _user;

        return {
            set : function (user) {
                $cookieStore.remove('lawyer.com.br.user');
                $cookieStore.put('lawyer.com.br.user', user);
                _user = user;
            },
            get : function () {
                if (_user === null || _user === undefined) {
                    _user = $cookieStore.get('lawyer.com.br.user');
                }
                return _user;
            }
        };
    }])
;
