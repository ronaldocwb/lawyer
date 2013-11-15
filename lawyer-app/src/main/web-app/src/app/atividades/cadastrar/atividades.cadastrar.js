angular.module('lawyer.atividades.cadastro', [
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('atividades.cadastrar', {
            url: '/cadastro',
            controller: 'CadastrarAtividadeController',
            templateUrl: 'atividades/cadastrar/atividades.cadastrar.tpl.html'
        });
    }])

    .controller('CadastrarAtividadeController', ['$scope', '$state', '$log', 'Atividade', 'Municipio', 'notifications', '$http', 'Pessoa', '$modal', 'Setor',
        function ($scope, $state, $log, Atividade, Municipio, notifications, $http, Pessoa, $modal, Setor) {
            $scope.tela = {
                cadastro : true,
                edicao : false
            };
            $scope.atividade = {
                telefones: [],
                responsaveis: [],
                enderecos: []
            };

            $scope.pushAtividadeListagem = function () {
                if ($scope.atividades) {
                    $scope.atividades.content.push($scope.atividade);
                }
            };

            $scope.salvar = function () {
                $scope.atividade = Atividade.save($scope.atividade, function () {
                    notifications.pushForCurrentRoute('atividade.salva', 'success', {nome: $scope.atividade.nomeFantasia});
                    $scope.pushAtividadeListagem();
                    $state.go('atividades.listar');
                });
            };

            $scope.salvarContinuar = function () {
                $scope.atividade = Atividade.save($scope.atividade, function () {
                    notifications.pushForCurrentRoute('atividade.salva', 'success', {nome: $scope.atividade.nomeFantasia});
                    $scope.pushAtividadeListagem();
                    $scope.atividade = {
                        telefones: [],
                        responsaveis: [],
                        enderecos: []
                    };
                });
            };

            $scope.add = function (key) {
                $scope.atividade[key].push({});
            };

            $scope.remove = function (key, $index) {
                $scope.atividade[key].splice($index, 1);
            };

            $scope.addSetor = function (name, $index) {
                var setor = {
                    nome : name
                };
                Setor.save(setor, function (setor) {
                    $scope.atividade.responsaveis[$index].setor = setor;
                    notifications.pushForCurrentRoute('setor.criado', 'success', { nome: name});
                });
            };

            $scope.addPessoa = function (name, $index) {
                var pessoa = {
                    nome : name
                };
                Pessoa.save(pessoa, function (pessoa) {
                    $scope.atividade.responsaveis[$index].pessoa = pessoa;
                    // emite a notificação para completar cadastro: i18n, nome, callback e objeto
                    notifications.pushCompletarCadastro('pessoa.completar.cadastro', { nome: name} , 'atividades.cadastrar.pessoa.callback', pessoa);
                });
            };

            $scope.$on('atividades.cadastrar.pessoa.callback', function ($event, pessoa) {
                $scope.completarPessoa(pessoa);
            });

            $scope.onSelectPessoa = function (pessoa, $index) {
                $scope.atividade.responsaveis[$index].pessoa = pessoa;
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
                    angular.forEach($scope.atividade.responsaveis, function (responsavel) {
                        if (responsavel.pessoa.uid === pessoaAtualizada.uid) {
                            index = $scope.atividade.responsaveis.indexOf(responsavel);
                        }
                    });
                    $scope.atividade.responsaveis[index].pessoa = pessoaAtualizada;
                    notifications.pushForCurrentRoute('pessoa.alterada', 'success', { nome: pessoaAtualizada.nome});
                });
            };

            $scope.voltar = function () {
                angular.noop($scope.modal ? $scope.modal.close(true) : $state.go('atividades.listar'));
            };

        }])

;