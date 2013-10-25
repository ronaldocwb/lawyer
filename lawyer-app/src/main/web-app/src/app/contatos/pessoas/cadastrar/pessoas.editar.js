angular.module('lawyer.pessoas.edicao', [
    ])

    .config(['$stateProvider',  function config($stateProvider) {
        $stateProvider.state('pessoas.editar', {
            url: '/editar/',
            controller: 'PessoaEdicaoController',
            templateUrl: 'contatos/pessoas/cadastrar/pessoas.cadastrar.tpl.html'
        });
    }])

    .controller('PessoaEdicaoController', ['$scope', 'notifications', '$log', 'Pessoa', '$state', '$stateParams', '$http', 'Empresa', '$modal',
        function ($scope, notifications, $log, Pessoa, $state, $stateParams, $http, Empresa, $modal) {

            $scope.tela = {
                cadastro : false,
                edicao : true
            };

            $scope.pessoa = $state.data;

            if ($state.data && $state.data.modal) {
                $scope.modal = $state.data.modal;
            }

            if (!$state.data && !$state.pessoa) {
                if ($stateParams.uid) {
                    $scope.pessoa = Pessoa.get({uid : $stateParams.uid});
                } else {
                    $state.go('pessoas.listar');
                }
            }

            $scope.salvar = function () {
                $scope.pessoa = Pessoa.update($scope.pessoa, function () {
                    notifications.pushForNextRoute('pessoa.alterada', 'success', {nome : $scope.pessoa.nome});
                    angular.noop($scope.modal ? $scope.modal.close(true) : $state.go('pessoas.listar'));
                });
            };

            $scope.voltar = function () {
                angular.noop($scope.modal ? $scope.modal.close(true) : $state.go('pessoas.listar'));
            };

            $scope.add = function (key) {
                $scope.pessoa[key].push({});
            };

            $scope.remove = function (key, $index) {
                $scope.pessoa[key].splice($index, 1);
            };

            $scope.getMunicipios = function (value) {
                return $http.get('/lawyer/api/municipios?q='+value)
                    .then(function(result){
                        return result.data;
                    });
            };

            $scope.getEmpresas = function (value) {
                return $http.get('/lawyer/api/empresas?q='+value+'&page=0&limit:5')
                    .then(function(results){
                        return results.data.content;
                    });
            };

            $scope.notification = {};
            $scope.addEmpresa = function ($item) {
                $scope.pessoa.empresa = {
                    nomeFantasia : $item,
                    telefones : [],
                    enderecos : []
                };
                $scope.pessoa.empresa = Empresa.save($scope.pessoa.empresa, function () {
                    $scope.notification = {
                        text : 'A empresa <b>' + $item + '</b> foi criada!'
                    };
                });
            };

            $scope.completarEmpresa = function () {
                $state.data = $scope.pessoa.empresa;
                $state.data.modal = $modal.open({
                    templateUrl: 'contatos/empresas/cadastrar/empresas.cadastrar.tpl.html',
                    controller: 'EmpresaEdicaoController',
                    resolve: {
                        empresa: function () {
                            return $scope.pessoa.empresa;
                        }
                    }
                });

                $state.data.modal.result.then(function (editar) {
                    if (editar === true) {
                        $state.data = $scope.pessoa.empresa;
                    }
                });
            };

        }])

;