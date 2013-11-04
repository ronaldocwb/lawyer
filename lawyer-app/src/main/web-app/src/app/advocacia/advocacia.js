angular.module('lawyer.configuracoes.advocacia', [
    'lawyer.Advocacia'
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
                advocacia: function (Advocacia) {
                    return Advocacia.get();
                }
            }
        });
    }])

    .controller('AdvocaciaController', ['$scope', 'advocacia',
        function ($scope, advocacia) {
            $scope.advocacia = advocacia;
        }])
;

