angular.module('lawyer.pessoas.edicao', [
    ])

    .config(['$stateProvider',  function config($stateProvider) {
        $stateProvider.state('pessoas.editar', {
            url: '/editar/:uid',
            controller: 'PessoaEdicaoController',
            templateUrl: 'contatos/pessoas/cadastrar/pessoas.cadastrar.tpl.html'
        });
    }])

    .controller('PessoaEdicaoController', ['$scope', 'notifications', '$log', 'Pessoa', '$state', '$stateParams', 'Empresa', '$modal',
        function ($scope, notifications, $log, Pessoa, $state, $stateParams, Empresa, $modal) {

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
                    angular.noop($scope.modal ? $scope.modal.close($scope.pessoa) : $state.go('pessoas.listar'));
                });
            };

            $scope.voltar = function () {
                angular.noop($scope.modal ? $scope.modal.close() : $state.go('pessoas.listar'));
            };

            $scope.add = function (key) {
                if (!$scope.pessoa[key]) {
                    $scope.pessoa[key] = [];
                }
                $scope.pessoa[key].push({});
            };

            $scope.remove = function (key, $index) {
                $scope.pessoa[key].splice($index, 1);
            };

            $scope.addEmpresa= function (name) {
                $scope.pessoa.empresa = {
                    nomeFantasia : name,
                    telefones : [],
                    enderecos : []
                };
                Empresa.save($scope.pessoa.empresa, function (empresa) {
                    $scope.pessoa.empresa = empresa;
                    // emite a notificação para completar cadastro: i18n, nome, callback e objeto
                    notifications.pushCompletarCadastro('empresa.completar.cadastro', { nome: name} , 'pessoa.cadastrar.empresa.callback', empresa);
                });
            };

            $scope.$on('pessoa.cadastrar.empresa.callback', function ($event, empresa) {
                $scope.completarEmpresa(empresa);
            });

            $scope.onSelectEmpresa = function (empresa) {
                $scope.pessoa.empresa = empresa;
            };

            $scope.completarEmpresa = function (empresa) {
                //envia o objeto de retorno no OK do modal para a popup de edicao, copia ele pra nao fazer bind automatico e aparecer alterando na tela de fundo.
                $state.data = angular.copy(empresa);
                $state.data.modal = $modal.open({
                    templateUrl: 'contatos/empresas/cadastrar/empresas.cadastrar.tpl.html',
                    controller: 'EmpresaEdicaoController',
                    resolve: {
                        empresa: function () {
                            return empresa;
                        }
                    }
                });

                $state.data.modal.result.then(function (empresaAtualizada) {
                    if (!empresaAtualizada) {
                        return;
                    }
                    // se atualizou a pessoa, ela existe e vamos substituir no array de responsaveis para a alteração ficar visivel na tela, e mostramos uma notificação de OK.
                    $scope.pessoa.empresa = empresaAtualizada;
                });
            };


        }])

;