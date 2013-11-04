angular.module('lawyer.empresas', [
        'lawyer.Empresa',
        'lawyer.Municipio',
        'lawyer.Responsavel',
        'lawyer.Pessoa',
        'lawyer.Setor'
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('empresas', {
            url: '/empresas',
            abstract: true,
            views: {
                "main": {
                    controller: 'EmpresaController',
                    template: '<div ui-view></div>'
                }
            },
            resolve: {
                empresas: function (Empresa) {
                    return Empresa.get();
                }
            }
        });
    }])

    .controller('EmpresaController', ['$scope', 'empresas',
        function ($scope, empresas) {
            $scope.empresas = empresas;
        }])

;
