angular.module('lawyer.atividades.edicao', [
    ])

    .config(['$stateProvider',  function config($stateProvider) {
        $stateProvider.state('atividades.editar', {
            url: '/editar/',
            controller: 'EditarAtividadeController',
            templateUrl: 'atividades/cadastrar/atividades.cadastrar.tpl.html'
        });
    }])

    .controller('EditarAtividadeController', ['$scope', 'notifications', '$log', 'Atividade', '$state', '$stateParams', '$http', 'Pessoa', '$modal', 'Setor',
        function ($scope, notifications, $log, Atividade, $state, $stateParams, $http, Pessoa, $modal, Setor) {
            $scope.tela = {
                cadastro : false,
                edicao : true
            };
            $scope.atividades = $state.data;

            if ($state.data && $state.data.modal) {
                $scope.modal = $state.data.modal;
            }

            if (!$state.data && !$state.atividade) {
                if ($stateParams.uid) {
                    $scope.atividade = Atividade.get({uid : $stateParams.uid});
                } else {
                    $state.go('atividades.listar');
                }
            }

            $scope.salvar = function () {
                console.log($scope.atividade);
                $scope.atividade = Atividade.update($scope.atividade, function () {
                    notifications.pushForCurrentRoute('atividade.alterada', 'success', {nome : $scope.atividade.nomeFantasia});
                    angular.noop($scope.modal ? $scope.modal.close($scope.atividade) : $state.go('atividades.listar'));
                });
            };

            $scope.add = function (key) {
                if (!$scope.atividade[key]) {
                    $scope.atividade[key] = [];
                }
                $scope.atividade[key].push({});
            };

            $scope.remove = function (key, $index) {
                $scope.atividade[key].splice($index, 1);
            };

            $scope.voltar = function () {
                angular.noop($scope.modal ? $scope.modal.close(true) : $state.go('atividades.listar'));
            };



            $scope.addPessoa = function (name, $index) {
                var pessoa = {
                    nome : name
                };
                Pessoa.save(pessoa, function (pessoa) {
                    $scope.atividade.responsaveis[$index].pessoa = pessoa;
                    // emite a notificação para completar cadastro: i18n, nome, callback e objeto
                    notifications.pushCompletarCadastro('atividades.completar.cadastro', { nome: name} , 'atividades.cadastrar.pessoa.callback', pessoa);
                });
            };

            $scope.$on('atividades.cadastrar.pessoa.callback', function ($event, pessoa) {
                $scope.completarPessoa(pessoa);
            });

            $scope.onSelectPessoa = function (pessoa, $index) {
                console.log(pessoa, $index);
                $scope.atividade.responsaveis[$index].pessoa = pessoa;
            };

            $scope.completarPessoa = function (pessoa) {
                //envia o objeto de retorno no OK do modal para a popup de edicao, copia ele pra nao fazer bind automatico e aparecer alterando na tela de fundo.
                $state.data = angular.copy(pessoa);
                $state.data.modal = $modal.open({
                    templateUrl: 'atividades/cadastrar/pessoas.cadastrar.tpl.html',
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

            $scope.getPessoas = function (value) {
                return $http.get('/lawyer/api/pessoas?q=' + value + '&page=0&limit:8')
                    .then(function (result) {
                        return result.data.content;
                    });
            };

            $scope.getSetores = function () {
                return $http.get('/lawyer/api/setores')
                    .then(function (result) {
                        return result.data;
                    });
            };

            $scope.getMunicipios = function (value) {
                return $http.get('/lawyer/api/municipios?q='+value)
                    .then(function(result){
                        return result.data;
                    });
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
        }])
;