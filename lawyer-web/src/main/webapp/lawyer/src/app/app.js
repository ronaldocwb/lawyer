angular.module('ngBoilerplate', [
  'templates-app',
  'templates-common',
  'ngBoilerplate.home',
  'ngBoilerplate.about',
  'ui.state',
  'ui.route'
]).config([
  '$stateProvider',
  '$urlRouterProvider',
  function myAppConfig($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/home');
  }
]).run([
  'titleService',
  function run(titleService) {
    titleService.setSuffix(' | ngBoilerplate');
  }
]).controller('AppCtrl', [
  '$scope',
  '$location',
  function AppCtrl($scope, $location) {
  }
]);
;