angular.module('lawyer.advogados', [
        'lawyer.Pessoa',
        'lawyer.Municipio',
        'lawyer.Advogado'
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('advogados', {
            url: '/advogados',
            abstract: true,
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

    .controller('AdvogadoController', ['$scope', 'advogados', '$http', function ($scope, advogados, $http) {
        $scope.advogados = advogados;

        $scope.getMunicipios = function (value) {
            return $http.get('/lawyer/api/municipios?q=' + value +'&page=0&limit:8')
                .then(function (results) {
                    return results.data;
                });
        };
        $scope.getEmpresas = function (value) {
            return $http.get('/lawyer/api/empresas?q=' + value + '&page=0&limit:8')
                .then(function (results) {
                    return results.data.content;
                });
        };
        $scope.getPessoas = function (value) {
            return $http.get('/lawyer/api/pessoas?q=' + value + '&page=0&limit:8')
                .then(function (results) {
                    return results.data.content;
                });
        };
    }])

;
