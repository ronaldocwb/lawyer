angular.module('lawyer.pessoas.edicao', [
    ])

    .config(['$stateProvider',  function config($stateProvider) {
        $stateProvider.state('contatos.editar-pessoa', {
            url: '/pessoas/editar/:uid',
            controller: 'EditarPessoaController',
            templateUrl: 'contatos/pessoas/cadastrar/pessoas.cadastrar.tpl.html'
        });
    }])

    .controller('EditarPessoaController', ['$scope', 'notifications', '$log', 'Contato', 'Pessoa', '$state', '$stateParams', '$modal',  'municipioAutocomplete', 'empresaAutocomplete',
        function ($scope, notifications, $log, Contato, Pessoa, $state, $stateParams, $modal, municipioAutocomplete, empresaAutocomplete) {
            $scope.blur = { error: false};
            $scope.tela = {
                cadastro : false,
                edicao : true
            };

            $scope.contato = $state.data;

            if ($state.data && $state.data.modal) {
                $scope.modal = $state.data.modal;
            }

            if (!$state.data && !$state.pessoa) {
                if ($stateParams.uid) {
                    $scope.contato = Contato.getPessoaByUid({pessoaUid : $stateParams.uid});
                } else {
                    $state.go('contatos.listar-pessoas');
                }
            }

            $scope.salvar = function () {
                $scope.contato.pessoa.empresa = $scope.contato.pessoa.empresa === '' ? null : $scope.contato.pessoa.empresa;
                $scope.contato = Contato.update($scope.contato, function () {
                    notifications.pushForNextRoute('pessoa.alterada', 'success', {nome : $scope.contato.pessoa.nome});
                    angular.noop($scope.modal ? $scope.modal.close($scope.contato) : $state.go('pessoas.listar'));
                });
            };

            $scope.voltar = function () {
                angular.noop($scope.modal ? $scope.modal.close() : $state.go('contatos.listar-pessoas'));
            };

            $scope.add = function (key) {
                if (!$scope.contato.pessoa[key]) {
                    $scope.contato.pessoa[key] = [];
                }
                $scope.contato.pessoa[key].push({});
            };

            $scope.remove = function (key, $index) {
                $scope.contato.pessoa[key].splice($index, 1);
            };

            $scope.addEmpresa= function (name) {
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
                    notifications.pushCompletarCadastro('empresa.completar.cadastro', { nome: name} , 'pessoa.cadastrar.empresa.callback', contato);
                });
            };

            $scope.$on('pessoa.cadastrar.empresa.callback', function ($event, contato) {
                $scope.completarEmpresa(contato);
            });

            $scope.onSelectEmpresa = function (contato) {
                $scope.contato.pessoa.empresa = contato;
            };

            $scope.completarEmpresa = function (contato) {
                //envia o objeto de retorno no OK do modal para a popup de edicao, copia ele pra nao fazer bind automatico e aparecer alterando na tela de fundo.
                $state.data = angular.copy(contato);
                $state.data.modal = $modal.open({
                    templateUrl: 'contatos/empresas/cadastrar/empresas.cadastrar.editar.tpl.html',
                    controller: 'EditarEmpresaController',
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
                    $scope.contato.pessoa.empresa = contato.empresa;
                });
            };

            $scope.getMunicipios = function (value) {
                return municipioAutocomplete.query(value);
            };

            $scope.getEmpresas = function (value) {
                return empresaAutocomplete.query(value);
            };

        }])

;