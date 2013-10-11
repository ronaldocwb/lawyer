angular.module('lawyer-login', [
        'ngResource',
        'ngCookies',
        'templates-app',
        'lawyer.account.popup',
        'ui.bootstrap'
    ])

    .controller('LoginController', ['$scope', '$resource', '$window', '$cookieStore', '$dialog', '$log', '$location', function ($scope, $resource, $window, $cookieStore, $dialog, $log, $location) {
        var l = 'lawyer';
        var u = 'user';
        var j = 'JSES';
        var i = 'SION';

        $cookieStore.remove(j+i+'ID');
        $cookieStore.remove(l+'.'+u);

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
                    $cookieStore.put(l+'.'+u, userVO);
                    $cookieStore.put("location", $location.path());

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

        $scope.createAccount = function () {
            var dialog = $dialog.dialog({
                backdropFade: true,
                dialogFade: true,
                controller: 'AccountPopUp'
            });
            dialog.open('templates/account/create.tpl.html');
        };

    }]);

