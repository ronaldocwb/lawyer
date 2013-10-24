angular.module('lawyer.empresas.cadastro', [
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('empresas.cadastrar', {
            url: '/cadastro',
            controller: 'EmpresaCadastroController',
            templateUrl: 'contatos/empresas/cadastrar/empresas.cadastrar.tpl.html'
        });
    }])

    .controller('EmpresaCadastroController', ['$scope', '$state', '$log', 'Empresa', 'Municipio', 'notifications', '$http', 'Responsavel',
        function ($scope, $state, $log, Empresa, Municipio, notifications, $http, Responsavel) {

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
                $log.debug('Enviando cadastro para o endpoint', $scope.empresa);
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

            $scope.getResponsaveis = function (value) {
                return $http.get('/lawyer/api/responsaveis?q=' + value + '&page=0&limit:8')
                    .then(function (result) {
                        return result.data.content;
                    });
            };

            $scope.addResponsavel = function (name) {
                var responsavel = {
                    nome : name
                };
                Responsavel.save(responsavel, function (result, $index) {
                    // TODO inserir no index certo do array
                });
            };

        }])

;