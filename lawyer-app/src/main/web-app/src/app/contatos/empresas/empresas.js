angular.module('lawyer.empresas', [
        'lawyer.Empresa',
        'lawyer.Municipio',
        'lawyer.Responsavel',
        'lawyer.Pessoa',
        'lawyer.Setor',
        'municipioAutocomplete',
        'setorAutocomplete',
        'pessoaAutocomplete'
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

    .controller('EmpresaController', ['$scope', 'empresas', '$state', 'municipioAutocomplete', 'pessoaAutocomplete', 'setorAutocomplete',
        function ($scope, empresas, $state, municipioAutocomplete, pessoaAutocomplete, setorAutocomplete) {
            $scope.empresas = empresas;

            $scope.getSetores = function (value) {
                return setorAutocomplete.query(value);
            };

            $scope.getMunicipios = function (value) {
                return municipioAutocomplete.query(value);
            };

            $scope.getPessoas = function (value) {
                return pessoaAutocomplete.query(value);
            };

        }])

;
