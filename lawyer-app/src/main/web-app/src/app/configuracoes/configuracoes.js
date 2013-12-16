angular.module('lawyer.configuracoes', [])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('configuracoes', {
            url: '/configuracoes',
            views: {
                "main": {
                    controller: 'ConfiguracoesController',
                    templateUrl: 'configuracoes/configuracoes.tpl.html'
                }
            }
        });
    }])

    .controller('ConfiguracoesController', ['$scope', '$log', function ($scope, $log) {

    }])

;

