angular.module('lawyer.advogados', [
        'lawyer.Pessoa',
        'lawyer.Municipio'
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('advogados', {
            url: '/advogados',
            abstract : true,
            views: {
                "main": {
                    controller: 'AdvogadoController',
                    template: '<div ui-view></div>'
                }
            },
            resolve: {
                advogados: function (Advogado) {
                    return Advogado.get();
                }
            }
        });
    }])

    .controller('AdvogadoController', ['$scope', 'advogados', function ($scope, advogados) {
            $scope.advogados = advogados;
        }])

;
