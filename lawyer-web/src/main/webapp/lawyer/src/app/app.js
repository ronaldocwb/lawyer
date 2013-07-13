angular.module('lawyer', [
  'templates-app',
  'templates-common',
  'lawyer.home',
  'lawyer.about',
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
    titleService.setSuffix(' | Sua advocacia de forma f\xe1cil.');
  }
]).controller('LawyerController', [
  '$scope',
  '$location',
  function AppCtrl($scope, $location) {
  }
]);
;