angular.module('lawyer.about', [
  'ui.state',
  'placeholders',
  'ui.bootstrap',
  'titleService'
]).config([
  '$stateProvider',
  function config($stateProvider) {
    $stateProvider.state('about', {
      url: '/about',
      views: {
        'main': {
          controller: 'AboutCtrl',
          templateUrl: 'about/about.tpl.html'
        }
      }
    });
  }
]).controller('AboutCtrl', [
  '$scope',
  'titleService',
  function AboutCtrl($scope, titleService) {
    titleService.setTitle('What is It?');
    $scope.dropdownDemoItems = [
      'The first choice!',
      'And another choice for you.',
      'but wait! A third!'
    ];
  }
]);
;