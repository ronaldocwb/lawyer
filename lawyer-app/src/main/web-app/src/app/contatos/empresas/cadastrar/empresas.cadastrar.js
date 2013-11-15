angular.module('lawyer.empresas.cadastro', [
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('empresas.cadastrar', {
            url: '/cadastro',
            controller: 'EmpresaCadastroController',
            templateUrl: 'contatos/empresas/cadastrar/empresas.cadastrar.tpl.html'
        });
    }])

    .controller('EmpresaCadastroController', ['$scope', '$state', '$log', 'Empresa', 'Municipio', 'notifications', '$http', 'Pessoa', '$modal', 'Setor',
        function ($scope, $state, $log, Empresa, Municipio, notifications, $http, Pessoa, $modal, Setor) {
            $scope.tela = {
                cadastro : true,
                edicao : false
            };
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

            $scope.salvar = function () {
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

            $scope.addSetor = function (name, $index) {
                var setor = {
                    nome : name
                };
                Setor.save(setor, function (setor) {
                    $scope.empresa.responsaveis[$index].setor = setor;
                    notifications.pushForCurrentRoute('setor.criado', 'success', { nome: name});
                });
            };

            $scope.addPessoa = function (name, $index) {
                var pessoa = {
                    nome : name
                };
                Pessoa.save(pessoa, function (pessoa) {
                    $scope.empresa.responsaveis[$index].pessoa = pessoa;
                    // emite a notificação para completar cadastro: i18n, nome, callback e objeto
                    notifications.pushCompletarCadastro('pessoa.completar.cadastro', { nome: name} , 'empresas.cadastrar.pessoa.callback', pessoa);
                });
            };

            $scope.$on('empresas.cadastrar.pessoa.callback', function ($event, pessoa) {
                $scope.completarPessoa(pessoa);
            });

            $scope.onSelectPessoa = function (pessoa, $index) {
                $scope.empresa.responsaveis[$index].pessoa = pessoa;
            };

            $scope.completarPessoa = function (pessoa) {
                //envia o objeto de retorno no OK do modal para a popup de edicao, copia ele pra nao fazer bind automatico e aparecer alterando na tela de fundo.
                $state.data = angular.copy(pessoa);
                $state.data.modal = $modal.open({
                    templateUrl: 'contatos/pessoas/cadastrar/pessoas.cadastrar.tpl.html',
                    controller: 'PessoaEdicaoController',
                    resolve: {
                        pessoa: function () {
                            return pessoa;
                        }
                    }
                });

                $state.data.modal.result.then(function (pessoaAtualizada) {
                    if (!pessoaAtualizada) {
                        return;
                    }
                    // se atualizou a pessoa, ela existe e vamos substituir no array de responsaveis para a alteração ficar visivel na tela, e mostramos uma notificação de OK.
                    var index = null;
                    angular.forEach($scope.empresa.responsaveis, function (responsavel) {
                        if (responsavel.pessoa.uid === pessoaAtualizada.uid) {
                            index = $scope.empresa.responsaveis.indexOf(responsavel);
                        }
                    });
                    $scope.empresa.responsaveis[index].pessoa = pessoaAtualizada;
                    notifications.pushForCurrentRoute('pessoa.alterada', 'success', { nome: pessoaAtualizada.nome});
                });
            };

            $scope.voltar = function () {
                angular.noop($scope.modal ? $scope.modal.close(true) : $state.go('empresas.listar'));
            };

        }])

;