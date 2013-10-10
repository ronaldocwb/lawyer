angular.module('lawyer.contatos', [
    ])

    .config(['$stateProvider',  function config($stateProvider) {
        $stateProvider.state('contatos', {
            url: '/contatos/',
            views: {
                "main": {
                    controller: 'ContatosController',
                    templateUrl: 'contatos/contatos.tpl.html'
                }
            }
        });
    }])
.controller('ContatosController', ['$scope', 'notifications', '$state', '$modal', '$log', 'Pessoa',
        function ($scope, notifications, $state, $modal, $log, Pessoa) {

        }])
;
