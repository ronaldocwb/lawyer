angular.module('lawyer.empresas.cadastro', [
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('empresas.cadastrar', {
            url: '/cadastro',
            controller: 'EmpresaCadastroController',
            templateUrl: 'contatos/empresas/cadastrar/empresas.cadastrar.tpl.html'
        });
    }])

    .controller('EmpresaCadastroController', ['$scope', '$state', '$log', 'Empresa', 'Municipio', 'notifications', '$http', 'Pessoa',
        function ($scope, $state, $log, Empresa, Municipio, notifications, $http, Pessoa) {

            $scope.empresa = {
                telefones: [],
                responsaveis: [],
                enderecos: []
            };

            $scope.pushEmpresaListagem = function () {
                if ($scope.empresas) {
                    $scope.empresas.content.push($scope.empresa);
                }
            };

            $scope.cadastrar = function () {
                $scope.empresa = Empresa.save($scope.empresa, function () {
                    notifications.pushForCurrentRoute('empresa.salva', 'success', {nome: $scope.empresa.nomeFantasia});
                    $scope.pushEmpresaListagem();
                    $state.go('empresas.listar');
                });
            };

            $scope.salvarContinuar = function () {
                $scope.empresa = Empresa.save($scope.empresa, function () {
                    notifications.pushForCurrentRoute('empresa.salva', 'success', {nome: $scope.empresa.nomeFantasia});
                    $scope.pushEmpresaListagem();
                    $scope.empresa = {
                        telefones: [],
                        responsaveis: [],
                        enderecos: []
                    };
                });
            };

            $scope.add = function (key) {
                $scope.empresa[key].push({});
            };

            $scope.remove = function (key, $index) {
                $scope.empresa[key].splice($index, 1);
            };

            $scope.getMunicipios = function (value) {
                return $http.get('/lawyer/api/municipios?q=' + value)
                    .then(function (result) {
                        return result.data;
                    });
            };

            $scope.getPessoas = function (value) {
                return $http.get('/lawyer/api/pessoas?q=' + value + '&page=0&limit:8')
                    .then(function (result) {
                        return result.data.content;
                    });
            };

            $scope.addPessoa = function (name, $index) {
                var pessoa = {
                    nome : name
                };
                Pessoa.save(pessoa, function (result) {
                    $scope.empresa.responsaveis[$index].pessoa = result;
                });
            };

            $scope.onSelectPessoa = function (pessoa, $index) {
                $scope.empresa.responsaveis[$index].pessoa = pessoa;
            };

        }])

;