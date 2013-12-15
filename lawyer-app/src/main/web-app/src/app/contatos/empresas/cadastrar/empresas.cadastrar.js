angular.module('lawyer.empresas.cadastro', [
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('contatos.cadastrar-empresa', {
            url: '/empresas/cadastro',
            controller: 'CadastrarEmpresaController',
            templateUrl: 'contatos/empresas/cadastrar/empresas.cadastrar.editar.tpl.html'
        });
    }])

    .controller('CadastrarEmpresaController', ['$scope', '$state', '$log', 'Contato', 'Municipio', 'notifications', '$http', '$modal', 'Setor',
        function ($scope, $state, $log, Contato, Municipio, notifications, $http, $modal, Setor) {
            $scope.tela = {
                cadastro: true,
                edicao: false
            };

            $scope.contato = {
                empresa: {
                    telefones: [],
                    responsaveis: [],
                    enderecos: []
                }
            };

            $scope.pushEmpresaListagem = function () {
                if ($scope.contatos) {
                    $scope.contatos.content.push($scope.contato);
                }
            };

            $scope.salvar = function () {
                angular.forEach($scope.contato.empresa.responsaveis, function (responsavel) {
                    responsavel.pessoa = responsavel.pessoa === '' || responsavel.pessoa === undefined ? null : responsavel.pessoa;
                    responsavel.setor = responsavel.setor === '' || responsavel.setor === undefined ? null : responsavel.setor;
                });
                $scope.contato = Contato.save($scope.contato, function () {
                    notifications.pushForCurrentRoute('empresa.salva', 'success', {nome: $scope.contato.empresa.nomeFantasia});
                    $scope.pushEmpresaListagem();
                    $state.go('contatos.listar-empresas');
                });
            };

            $scope.salvarContinuar = function () {
                angular.forEach($scope.contato.empresa.responsaveis, function (responsavel) {
                    responsavel.pessoa = responsavel.pessoa === '' || responsavel.pessoa === undefined ? null : responsavel.pessoa;
                    responsavel.setor = responsavel.setor === '' || responsavel.setor === undefined ? null : responsavel.setor;
                });
                $scope.contato = Contato.save($scope.contato, function () {
                    notifications.pushForCurrentRoute('empresa.salva', 'success', {nome: $scope.contato.empresa.nomeFantasia});
                    $scope.pushEmpresaListagem();
                    $scope.contato.empresa = {
                        telefones: [],
                        responsaveis: [],
                        enderecos: []
                    };
                });
            };

            $scope.add = function (key) {
                $scope.contato.empresa[key].push({});
            };

            $scope.remove = function (key, $index) {
                $scope.contato.empresa[key].splice($index, 1);
            };

            $scope.addSetor = function (name, $index) {
                var setor = {
                    nome: name
                };
                Setor.save(setor, function (setor) {
                    $scope.contato.empresa.responsaveis[$index].setor = setor;
                    notifications.pushForCurrentRoute('setor.criado', 'success', { nome: name});
                });
            };

            $scope.addPessoa = function (name, $index) {
                var contatoPessoa = {
                        pessoa: {
                            nome: name
                        }
                    };
                Contato.save(contatoPessoa, function (contato) {
                    $scope.contato.empresa.responsaveis[$index].pessoa = contato.pessoa;
                    // emite a notificação para completar cadastro: i18n, nome, callback e objeto
                    notifications.pushCompletarCadastro('pessoa.completar.cadastro', { nome: name}, 'empresas.cadastrar.pessoa.callback', contato);
                });
            };

            $scope.$on('empresas.cadastrar.pessoa.callback', function ($event, contato) {
                $scope.completarPessoa(contato);
            });

            $scope.onSelectPessoa = function (pessoa, $index) {
                $scope.contato.empresa.responsaveis[$index].pessoa = pessoa;
            };

            $scope.completarPessoa = function (contato) {
                //envia o objeto de retorno no OK do modal para a popup de edicao, copia ele pra nao fazer bind automatico e aparecer alterando na tela de fundo.
                $state.data = angular.copy(contato);
                $state.data.modal = $modal.open({
                    templateUrl: 'contatos/pessoas/cadastrar/pessoas.cadastrar.tpl.html',
                    controller: 'EditarPessoaController',
                    resolve: {
                        contato: function () {
                            return contato;
                        }
                    }
                });

                $state.data.modal.result.then(function (contato) {
                    if (!contato) {
                        return;
                    }
                    // se atualizou a pessoa, ela existe e vamos substituir no array de responsaveis para a alteração ficar visivel na tela, e mostramos uma notificação de OK.
                    var index = null;
                    angular.forEach($scope.contato.empresa.responsaveis, function (responsavel) {
                        if (responsavel.pessoa.uid === contato.pessoa.uid) {
                            index = $scope.contato.empresa.responsaveis.indexOf(responsavel);
                        }
                    });
                    $scope.contato.empresa.responsaveis[index].pessoa = contato.pessoa;
                    notifications.pushForCurrentRoute('pessoa.alterada', 'success', { nome: contato.pessoa.nome});
                });
            };

            $scope.voltar = function () {
                angular.noop($scope.modal ? $scope.modal.close(true) : $state.go('contatos.listar-empresas'));
            };

            $scope.blur = { error: false};

        }])



;