
angular.module('lawyer.empresas.edicao', [
    ])

    .config(['$stateProvider',  function config($stateProvider) {
        $stateProvider.state('contatos.editar-empresa', {
            url: '/empresas/editar/',
            controller: 'EditarEmpresaController',
            templateUrl: 'contatos/empresas/cadastrar/empresas.cadastrar.editar.tpl.html'
        });
    }])

    .controller('EditarEmpresaController', ['$scope', 'notifications', '$log', 'Contato', '$state', '$stateParams', '$modal', 'Setor', 'municipioAutocomplete', 'pessoaAutocomplete', 'setorAutocomplete',
        function ($scope, notifications, $log, Contato, $state, $stateParams, $modal, Setor, municipioAutocomplete, pessoaAutocomplete, setorAutocomplete) {

            $scope.tela = {
                cadastro : false,
                edicao : true
            };
            $scope.blur = { error: false};
            $scope.contato = $state.data;


            if ($state.data && $state.data.modal) {
                $scope.modal = $state.data.modal;
            }

            if (!$state.data && !$state.empresa) {
                if ($stateParams.uid) {
                    $scope.contato.empresa = Contato.getEmpresaByUid({empresaUid : $stateParams.uid});
                } else {
                    $state.go('contatos.listar-empresas');
                }
            }

            $scope.salvar = function () {
                $scope.contato = Contato.update($scope.contato, function () {
                    notifications.pushForCurrentRoute('empresa.alterada', 'success', {nome : $scope.contato.empresa.nomeFantasia});
                    angular.noop($scope.modal ? $scope.modal.close($scope.contato) : $state.go('contatos.listar-empresas'));
                });
            };

            $scope.add = function (key) {
                if (!$scope.contato.empresa[key]) {
                    $scope.contato.empresa[key] = [];
                }
                $scope.contato.empresa[key].push({});
            };

            $scope.remove = function (key, $index) {
                $scope.contato.empresa[key].splice($index, 1);
            };

            $scope.voltar = function () {
                angular.noop($scope.modal ? $scope.modal.close(true) : $state.go('contatos.listar-empresas'));
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
                    notifications.pushCompletarCadastro('pessoa.completar.cadastro', { nome: name} , 'empresas.cadastrar.pessoa.callback', contato);
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

            $scope.addSetor = function (name, $index) {
                var setor = {
                    nome : name
                };
                Setor.save(setor, function (setor) {
                    $scope.contato.empresa.responsaveis[$index].setor = setor;
                    notifications.pushForCurrentRoute('setor.criado', 'success', { nome: name});
                });
            };

            $scope.getSetores = function (value) {
                return setorAutocomplete.query(value);
            };

            $scope.getMunicipios = function (value) {
                return municipioAutocomplete.query(value);
            };

            $scope.getPessoas = function (value) {
                return pessoaAutocomplete.query(value);
            };
        }])
;