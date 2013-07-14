angular.module( 'lawyer.home', [
  'ui.state',
  'titleService',
  'plusOne'
])

.config(function config( $stateProvider ) {
  $stateProvider.state( 'home', {
    url: '/home',
    views: {
      "main": {
        controller: 'HomeController',
        templateUrl: 'home/home.tpl.html'
      }
    }
  });
})

.controller( 'HomeController', function HomeController( $scope, titleService ) {
  titleService.setTitle( 'Home' );
})
;

