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

    .controller('EmpresaController', ['$scope', 'empresas', 'notifications', '$state', '$http',
        function ($scope, empresas, notifications, $state, $http) {
            $scope.empresas = empresas;

            $scope.getSetores = function (value) {
                return $http.get('/lawyer/api/setores?q=' + value + '&page=0&limit:8')
                    .then(function (result) {
                        return result.data.content;
                    });
            };

            $scope.getMunicipios = function (value) {
                return $http.get('/lawyer/api/municipios?q=' + value + '&page=0&limit:8')
                    .then(function (result) {
                        return result.data.content;
                    });
            };

            $scope.getPessoas = function (value) {
                return $http.get('/lawyer/api/pessoas?q=' + value + '&page=0&limit:8')
                    .then(function (result) {
                        return result.data.content;
                    });
            };

        }])

;
