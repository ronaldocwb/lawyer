angular.module('ngBoilerplate.home', [
  'ui.state',
  'titleService',
  'plusOne'
]).config([
  '$stateProvider',
  function config($stateProvider) {
    $stateProvider.state('home', {
      url: '/home',
      views: {
        'main': {
          controller: 'HomeCtrl',
          templateUrl: 'home/home.tpl.html'
        }
      }
    });
  }
]).controller('HomeCtrl', [
  '$scope',
  'titleService',
  function HomeController($scope, titleService) {
    titleService.setTitle('Home');
  }
]);
;