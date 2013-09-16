/**
 * @ngdoc object
 * @name lawyer.home
 * @description
 * Essa é a view inicial do app, responsável por carregar o "dashboard" inicial e apresentar algumas features.
 */
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

    .controller('HomeController', ['$scope', 'i18nNotifications', '$timeout', function ($scope, i18nNotifications, $timeout) {
    }])

;

