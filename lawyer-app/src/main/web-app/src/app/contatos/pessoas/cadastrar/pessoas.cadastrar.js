angular.module('lawyer.pessoas.cadastro', [
    ])

    .config(['$stateProvider',  function config($stateProvider) {
        $stateProvider.state('pessoas.cadastrar', {
            url: '/cadastro',
            controller: 'PessoaCadastroController',
            templateUrl: 'contatos/pessoas/cadastrar/pessoas.cadastrar.tpl.html'
        });
    }])

    .controller('PessoaCadastroController', ['$scope', '$state', '$log', 'Pessoa', 'Municipio', 'notifications', 'Empresa', '$modal',
        function ($scope, $state, $log, Pessoa, Municipio, notifications, Empresa, $modal) {

            $scope.tela = {
                cadastro : true,
                edicao : false
            };

            $scope.pessoa = {
                telefones : [],
                emails : [],
                enderecos : []
            };

            $scope.pushPessoaListagem = function () {
                if ($scope.pessoas) {
                    $scope.pessoas.content.push($scope.pessoa);
                }
            };

            $scope.salvar = function () {
                $scope.pessoa = Pessoa.save($scope.pessoa, function () {
                    notifications.pushForNextRoute('pessoa.salva', 'success', {nome : $scope.pessoa.nome});
                    $scope.pushPessoaListagem();
                    angular.noop($scope.modal ? $scope.modal.close(true) : $state.go('pessoas.listar'));
                }, function () {
                    notifications.pushForCurrentRoute('pessoa.salva.erro', 'error', {nome : $scope.pessoa.nome});
                });
            };

            $scope.salvarContinuar = function () {
                $scope.pessoa = Pessoa.save($scope.pessoa, function () {
                    notifications.pushForCurrentRoute('pessoa.salva', 'success', {nome : $scope.pessoa.nome});
                    $scope.pushPessoaListagem();
                    $scope.pessoa = {
                        telefones : [],
                        enderecos : [],
                        emails : []
                    };
                }, function () {
                    notifications.pushForCurrentRoute('pessoa.salva.erro', 'error', {nome : $scope.pessoa.nome});
                });
            };

            $scope.add = function (key) {
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

            $scope.voltar = function () {
                angular.noop($scope.modal ? $scope.modal.close(true) : $state.go('pessoas.listar'));
            };


    }])

;
