angular.module('lawyer.configuracoes.advocacia', [
    'lawyer.Cliente'
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('advocacia', {
            url: '/advocacia',
            views: {
                "main": {
                    controller: 'AdvocaciaController',
                    templateUrl: 'advocacia/advocacia.tpl.html'
                }
            },
            resolve: {
                advocacia: function (Cliente) {
                    return Cliente.get();
                }
            }
        });
    }])

    .controller('AdvocaciaController', ['$scope', 'advocacia',
        function ($scope, advocacia) {
            $scope.advocacia = advocacia;
        }])
;

