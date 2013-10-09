/**
 * @ngdoc object
 * @name lawyer.home
 * @description
 * Essa � a view inicial do app, respons�vel por carregar o "dashboard" inicial e apresentar algumas features.
 */
angular.module('lawyer.home', [
        'ui.router.state'
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

    .controller('HomeController', ['$scope', 'notifications', '$timeout', function ($scope, notifications, $timeout) {
    }])

;

