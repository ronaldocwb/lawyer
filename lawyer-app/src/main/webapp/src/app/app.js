angular.module( 'lawyer', [
  'templates-app',
  'templates-common',
  'lawyer.home',
  'lawyer.about',
  'ui.state',
  'ui.route'
])

.config( function myAppConfig ( $stateProvider, $urlRouterProvider ) {
  $urlRouterProvider.otherwise( '/home' );
})

.run( function run ( titleService ) {
  titleService.setSuffix( ' | Sua advocacia de forma fácil.' );
})

.controller( 'LawyerController', function AppCtrl ( $scope, $location ) {
})

;

