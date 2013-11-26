angular.module('lawyer.advogados', [
        'lawyer.Pessoa',
        'lawyer.Municipio',
        'lawyer.Advogado',
        'pessoaAutocomplete',
        'empresaAutocomplete',
        'municipioAutocomplete'
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

    .controller('AdvogadoController', ['$scope', 'advogados', 'pessoaAutocomplete', 'empresaAutocomplete', 'municipioAutocomplete', function ($scope, advogados, pessoaAutocomplete, empresaAutocomplete, municipioAutocomplete) {
        $scope.advogados = advogados;

        $scope.getMunicipios = function (value) {
            return municipioAutocomplete.query(value);
        };
        $scope.getEmpresas = function (value) {
            return empresaAutocomplete.query(value);
        };
        $scope.getPessoas = function (value) {
            return pessoaAutocomplete.query(value);
        };
    }])

;
