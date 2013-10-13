/**
 * @ngdoc object
 * @name lawyer.home
 * @description
 * Essa � a view inicial do app, respons�vel por carregar o "dashboard" inicial e apresentar algumas features.
 */
angular.module('lawyer.painel', [
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('painel', {
            url: '/painel',
            views: {
                "main": {
                    controller: 'PainelController',
                    templateUrl: 'painel/painel.tpl.html'
                }
            }
        });
    }])

    .controller('PainelController', ['$scope', function ($scope) {
    }])

;

