angular.module('lawyer.pessoas.cadastro', [
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('contatos.cadastrar-pessoa', {
            url: '/pessoas/cadastro',
            controller: 'PessoaCadastroController',
            templateUrl: 'contatos/pessoas/cadastrar/pessoas.cadastrar.tpl.html'
        });
    }])

    .controller('PessoaCadastroController', ['$scope', '$state', '$log', 'Contato', 'Municipio', 'notifications', '$modal',
        function ($scope, $state, $log, Contato, Municipio, notifications, $modal) {
            $scope.tela = {
                cadastro: true,
                edicao: false
            };

            $scope.contato = {
                pessoa : {
                    telefones: [],
                    emails: [],
                    enderecos: []
                }
            };

            $scope.pushPessoaListagem = function () {
                if (!angular.equals($scope.pessoas, {})) {
                    $scope.pessoas.content.push($scope.contato);
                }
                if (!angular.equals($scope.contatos , {})) {
                    $scope.contatos.content.push($scope.contato);
                }
            };

            $scope.salvar = function () {
                $scope.contato = Contato.save($scope.contato, function () {
                    notifications.pushForNextRoute('pessoa.salva', 'success', {nome: $scope.contato.pessoa.nome});
                    $scope.pushPessoaListagem();
                    angular.noop($scope.modal ? $scope.modal.close(true) : $state.go('contatos.listar-pessoas'));
                }, function () {
                    notifications.pushForCurrentRoute('pessoa.salva.erro', 'error', {nome: $scope.contato.pessoa.nome});
                });
            };

            $scope.salvarContinuar = function () {
                $scope.contato = Contato.save($scope.contato, function () {
                    notifications.pushForCurrentRoute('pessoa.salva', 'success', {nome: $scope.contato.pessoa.nome});
                    $scope.pushPessoaListagem();
                    $scope.contato.pessoa = {
                        telefones: [],
                        enderecos: [],
                        emails: []
                    };
                }, function () {
                    notifications.pushForCurrentRoute('pessoa.salva.erro', 'error', {nome: $scope.contato.pessoa.nome});
                });
            };

            $scope.add = function (key) {
                $scope.contato.pessoa[key].push({});
            };

            $scope.remove = function (key, $index) {
                $scope.contato.pessoa[key].splice($index, 1);
            };

            $scope.addEmpresa = function (name) {
                $scope.contatoEmpresa = {
                    empresa: {
                        nomeFantasia: name,
                        telefones: [],
                        enderecos: []
                    },
                    tipoContato : $scope.contato.tipoContato
                };
                Contato.save($scope.contatoEmpresa, function (contato) {
                    $scope.contato.pessoa.empresa = contato.empresa;
                    // emite a notificação para completar cadastro: i18n, nome, callback e objeto
                    notifications.pushCompletarCadastro('empresa.completar.cadastro', { nome: name}, 'pessoa.cadastrar.empresa.callback', contato);
                });
            };

            $scope.$on('pessoa.cadastrar.empresa.callback', function ($event, contato) {
                $scope.completarEmpresa(contato);
            });

            $scope.onSelectEmpresa = function (contato) {
                $scope.contato.pessoa.empresa = contato.empresa;
            };

            $scope.completarEmpresa = function (contato) {
                //envia o objeto de retorno no OK do modal para a popup de edicao, copia ele pra nao fazer bind automatico e aparecer alterando na tela de fundo.
                $state.data = angular.copy(contato);
                $state.data.modal = $modal.open({
                    templateUrl: 'contatos/empresas/cadastrar/empresas.cadastrar.editar.tpl.html',
                    controller: 'EditarEmpresaController',
                    resolve: {
                        empresa: function () {
                            return contato;
                        }
                    }
                });

                $state.data.modal.result.then(function (empresaAtualizada) {
                    if (!empresaAtualizada) {
                        return;
                    }
                    // se atualizou a pessoa, ela existe e vamos substituir no array de responsaveis para a alteração ficar visivel na tela, e mostramos uma notificação de OK.
                    $scope.contato.pessoa.empresa = empresaAtualizada.empresa;
                });
            };

            $scope.voltar = function () {
                angular.noop($scope.modal ? $scope.modal.close(true) : $state.go('contatos.listar-pessoas'));
            };

            $scope.blur = { error: false};

        }])

;
