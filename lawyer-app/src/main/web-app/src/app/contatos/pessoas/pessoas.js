angular.module('lawyer.pessoas', [
        'lawyer.Pessoa',
        'lawyer.Municipio',
        'lawyer.Empresa',
        'municipioAutocomplete',
        'empresaAutocomplete'
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

    .controller('PessoaController', ['$scope', 'pessoas', 'municipioAutocomplete', 'empresaAutocomplete', function ($scope, pessoas, municipioAutocomplete, empresaAutocomplete) {
        $scope.pessoas = pessoas;

        $scope.getMunicipios = function (value) {
            return municipioAutocomplete.query(value);
        };

        $scope.getEmpresas = function (value) {
            return empresaAutocomplete.query(value);
        };

    }])
;
