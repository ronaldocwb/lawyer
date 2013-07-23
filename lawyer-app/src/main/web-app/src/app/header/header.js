angular.module('lawyer.header', ['ui.state'])

    .config(function ($stateProvider) {
        $stateProvider.state('header', {
            url : '/:q',
            views: {
                'header' : {
                    controller: 'HeaderController',
                    templateUrl: 'header/header.tpl.html'
                }
            }
        });
    })

    .controller('HeaderController', ['$scope', function ($scope) {
        console.log('I\'m Here niggaz');

    }])
;

