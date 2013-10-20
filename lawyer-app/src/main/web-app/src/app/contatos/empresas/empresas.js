angular.module('lawyer.empresas', [
        'lawyer.Empresa',
        'lawyer.Municipio'
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('empresas', {
            url: '/empresas',
            abstract : true,
            views: {
                "main": {
                    controller: 'EmpresaController',
                    template: '<div ui-view></div>'
                }
            }
        });
    }])

    .controller('EmpresaController', ['$scope', '$location', '$state', '$log',
        function ($scope, $location, $state, $log) {
        }])

;
