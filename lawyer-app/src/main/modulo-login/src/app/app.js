angular.module('lawyer-login', ['ngResource', 'ngCookies'])

    .controller('LoginController', ['$scope', '$resource', '$window', '$cookieStore', function ($scope, $resource, $window, $cookieStore) {

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
                alert(userVO);
                if (userVO && userVO.login) {
                    $window.location = '/laywer/secure/';
                }
                $cookieStore.put(userVO.login);
                $cookieStore.put(userVO.token);
            }, function fail() {
                $scope.fail = true;
                $scope.processing = false;
                angular.element('body').css('cursor', 'auto');
                $scope.user.senha = '';


            });

        };
    }]);

