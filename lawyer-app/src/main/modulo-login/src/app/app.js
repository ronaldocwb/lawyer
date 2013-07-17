angular.module('lawyer-login', ['ngResource', 'ngCookies'])

    .controller('LoginController', ['$scope', '$resource', '$window', '$cookieStore', function ($scope, $resource, $window, $cookieStore) {

        $cookieStore.remove('JSESSIONID');
        $cookieStore.remove('lawyer.email');
        $cookieStore.remove('lawyer.token');

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
                console.log(userVO);
                if (userVO && userVO.email) {
                    $window.location = '/laywer/secure/';
                    $cookieStore.put('lawyer.login', userVO.login);
                    $cookieStore.put('lawyer.token', userVO.token);
                }
                angular.element('body').css('cursor', 'auto');

            }, function fail() {
                $scope.fail = true;
                $scope.processing = false;
                angular.element('body').css('cursor', 'auto');
                $scope.user.senha = '';


            });

        };
    }]);

