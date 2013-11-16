angular.module('lawyer.pessoas', [
        'lawyer.Pessoa',
        'lawyer.Municipio',
        'lawyer.Empresa'
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('pessoas', {
            url: '/pessoas',
            abstract: true,
            views: {
                "main": {
                    controller: 'PessoaController',
                    template: '<div ui-view></div>'
                }
            },
            resolve: {
                pessoas: function (Pessoa) {
                    return Pessoa.get();
                }
            }
        });
    }])

    .controller('PessoaController', ['$scope', 'pessoas', '$http', function ($scope, pessoas, $http) {
        $scope.pessoas = pessoas;

        $scope.getMunicipios = function (value) {
            return $http.get('/lawyer/api/municipios?q='+value)
                .then(function(results){
                    return results.data.content;
                });
        };
        $scope.getEmpresas = function (value) {
            return $http.get('/lawyer/api/empresas?q='+value+'&page=0&limit:5')
                .then(function(results){
                    return results.data.content;
                });
        };

    }])

;
