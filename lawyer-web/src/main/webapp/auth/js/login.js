angular.module('lawyer-login', ['ngResource'])

.controller('LoginController', ['$scope', '$resource', '$window', function ($scope, $resource, $window) {

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
         Auth.save($scope.user, function () {
             $window.location = '/secure/';

         }, function () {
             $scope.fail = true;
             $scope.processing = false;
             angular.element('body').css('cursor', 'auto');
             $scope.user.password = '';
         });

     }
}]);

