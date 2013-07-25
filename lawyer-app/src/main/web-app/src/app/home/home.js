angular.module('lawyer.home', [
        'ui.state'
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('home', {
            url: '/home',
            views: {
                "main": {
                    controller: 'HomeController',
                    templateUrl: 'home/home.tpl.html'
                }
            }
        });
    }])

    .controller('HomeController', ['$scope', function ($scope) {
        console.log('HomeController');
    }])

;

