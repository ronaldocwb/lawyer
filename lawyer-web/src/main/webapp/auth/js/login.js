angular.module('lawyer-login', ['ngResource', 'ngCookies'])

.controller('LoginController', ['$scope', '$resource', '$window', '$cookieStore', function ($scope, $resource, $window, $cookieStore) {

     $scope.processing = false;
     $scope.user = {
        login : 'developer@lawyer.com.br',
         password : '123'
     };
1
     $scope.authenticate = function () {
         angular.element('body').css('cursor', 'wait');
         $scope.processing = true;

         var Auth = $resource('http://localhost:8080/lawyer/auth/authenticate', {});
         Auth.save($scope.user, function (userVO) {
             $window.location = '/secure/';
             $cookieStore.put(userVO.login);
             $cookieStore.put(userVO.token);
         }, function () {
             $scope.fail = true;
             $scope.processing = false;
             angular.element('body').css('cursor', 'auto');
             $scope.user.password = '';


});

     }
}]);

