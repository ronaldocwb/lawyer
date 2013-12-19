angular.module('lawyer.contatos', [
        'lawyer.Empresa',
        'lawyer.Municipio',
        'lawyer.Responsavel',
        'lawyer.Pessoa',
        'lawyer.Contato',
        'lawyer.Setor',
        'municipioAutocomplete',
        'setorAutocomplete',
        'pessoaAutocomplete',
        'empresaAutocomplete'
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('contatos', {
            url: '/contatos',
            abstract: true,
            views: {
                "main": {
                    controller: 'ContatosController',
                    templateUrl: 'contatos/contatos.tpl.html'
                }
            }
        });
    }])

    .controller('ContatosController', ['$scope', 'Contato', '$state', 'municipioAutocomplete', 'pessoaAutocomplete', 'setorAutocomplete', 'empresaAutocomplete',
        function ($scope, Contato, $state, municipioAutocomplete, pessoaAutocomplete, setorAutocomplete, empresaAutocomplete) {

            $scope.contatos = {};
            $scope.pessoas = {};
            $scope.empresas = {};
            $scope.usuarios = {};

            $scope.getSetores = function (value) {
                return setorAutocomplete.query(value);
            };

            $scope.getMunicipios = function (value) {
                return municipioAutocomplete.query(value);
            };

            $scope.getPessoas = function (value) {
                return pessoaAutocomplete.query(value);
            };

            $scope.getMunicipios = function (value) {
                return municipioAutocomplete.query(value);
            };

            $scope.getEmpresas = function (value) {
                return empresaAutocomplete.query(value);
            };

            $scope.fetchPessoas = function () {
                $scope.pessoas = Contato.getPessoas();
            };

            $scope.fetchEmpresas = function () {
                $scope.empresas = Contato.getEmpresas();
            };

            $scope.fetchContatos = function () {
                $scope.contatos = Contato.get();
            };
        }])
;

