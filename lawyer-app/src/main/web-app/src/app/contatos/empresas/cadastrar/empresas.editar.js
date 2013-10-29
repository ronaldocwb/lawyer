
angular.module('lawyer.empresas.edicao', [
    ])

    .config(['$stateProvider',  function config($stateProvider) {
        $stateProvider.state('empresas.editar', {
            url: '/editar/',
            controller: 'EmpresaEdicaoController',
            templateUrl: 'contatos/empresas/cadastrar/empresas.cadastrar.tpl.html'
        });
    }])

    .controller('EmpresaEdicaoController', ['$scope', 'notifications', '$log', 'Empresa', '$state', '$stateParams', '$http', 'Pessoa', '$modal',
        function ($scope, notifications, $log, Empresa, $state, $stateParams, $http, Pessoa, $modal) {
            $scope.tela = {
                cadastro : false,
                edicao : true
            };
            $scope.empresa = $state.data;

            if ($state.data && $state.data.modal) {
                $scope.modal = $state.data.modal;
            }

            if (!$state.data && !$state.empresa) {
                if ($stateParams.uid) {
                    $scope.empresa = Empresa.get({uid : $stateParams.uid});
                } else {
                    $state.go('empresas.listar');
                }
            }

            $scope.salvar = function () {
                console.log($scope.empresa);
                $scope.empresa = Empresa.update($scope.empresa, function () {
                    notifications.pushForCurrentRoute('empresa.alterada', 'success', {nome : $scope.empresa.nomeFantasia});
                    angular.noop($scope.modal ? $scope.modal.close($scope.empresa) : $state.go('empresas.listar'));
                });
            };

            $scope.add = function (key) {
                if (!$scope.empresa[key]) {
                    $scope.empresa[key] = [];
                }
                $scope.empresa[key].push({});
            };

            $scope.remove = function (key, $index) {
                $scope.empresa[key].splice($index, 1);
            };

            $scope.voltar = function () {
                angular.noop($scope.modal ? $scope.modal.close(true) : $state.go('empresas.listar'));
            };

            $scope.getMunicipios = function (value) {
                return $http.get('/lawyer/api/municipios?q='+value)
                    .then(function(result){
                        return result.data;
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
                console.log(pessoa, $index);
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

            $scope.getPessoas = function (value) {
                return $http.get('/lawyer/api/pessoas?q=' + value + '&page=0&limit:8')
                    .then(function (result) {
                        return result.data.content;
                    });
            };
        }])
;