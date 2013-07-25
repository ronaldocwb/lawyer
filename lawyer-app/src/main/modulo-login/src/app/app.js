angular.module('lawyer-login', ['ngResource', 'ngCookies'])

    .controller('LoginController', ['$scope', '$resource', '$window', '$cookieStore', function ($scope, $resource, $window, $cookieStore) {
        var l = 'lawyer';
        var e = 'email';
        var t = 'token';
        var p = 'permissoes';

        $cookieStore.remove('JSESSIONID');
        $cookieStore.remove(l+'.'+e);
        $cookieStore.remove(l+'.'+t);

        $scope.processing = false;
        $scope.user = {
            email : 'developer@lawyer.com.br',
            senha : '123'
        };

        $scope.authenticate = function () {
            angular.element('body').css('cursor', 'wait');
            $scope.processing = true;

            var Auth = $resource('auth/authenticate', {});
            Auth.save($scope.user, function success(userVO) {
                if (typeof userVO !== 'undefined' && userVO.email) {
                    $cookieStore.put(l+'.'+e, userVO.email);
                    $cookieStore.put(l+'.'+t, userVO.token);
                    $cookieStore.put(l+'.'+p, userVO.authorities);
                    $window.location = '/lawyer/secure/';
                }
                angular.element('body').css('cursor', 'auto');

            }, function fail(error) {
                console.log(error);
                $scope.fail = true;
                $scope.processing = false;
                angular.element('body').css('cursor', 'auto');
                $scope.user.senha = '';
            });

        };
    }]);

