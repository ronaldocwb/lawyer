angular.module('lawyer.pessoas.cadastro', [
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('contatos.cadastrar-pessoa', {
            url: '/pessoas/cadastro',
            controller: 'PessoaCadastroController',
            templateUrl: 'contatos/pessoas/cadastrar/pessoas.cadastrar.tpl.html'
        });
    }])

    .controller('PessoaCadastroController', ['$scope', '$state', '$log', 'Contato', 'Municipio', 'notifications', '$modal', '$compile', '$http',
        function ($scope, $state, $log, Contato, Municipio, notifications, $modal, $compile, $http) {
            $scope.tela = {
                cadastro: true,
                edicao: false
            };

            $scope.remote = function (query) {
                $http.get('/lawyer/api/empresas?q=' + query.term + '&page=0&limit=8')
                    .then(function (res) {
                        var empresas = { results: [] };

                        angular.forEach(res.data.content, function (empresa) {
                            empresas.results.push({
                                text: empresa.nomeFantasia,
                                id: angular.toJson(empresa)
                            });
                        });
                        query.callback(empresas);
                    });
            };
            $scope.initS = function (term, t) {
                var uid = term[0].value;
                console.log(term[0].obj);
                console.log($scope.contato.pessoa.empresa2);
                $scope.contato.pessoa.empresa = angular.fromJson(uid);

            };

            $scope.select2Options = {
                formatNoMatches: function (term) {
                    $scope.termo = term;
                    var msg = '<p>Nenhum resultado encontrado para: ' + term + ' <button data-ng-click="addEmpresa(termo)" type="button" class="btn btn-mini btn-info ng-binding">Adicionar Novo</button></p>';
                    return $.wrap(msg);
                },
                query: $scope.remote,
                initSelection: $scope.initS

            };

            $scope.contato = {
                pessoa: {
                    telefones: [],
                    emails: [],
                    enderecos: []
                }
            };

            $scope.pushPessoaListagem = function () {
                if (!angular.equals($scope.pessoas, {})) {
                    $scope.pessoas.content.push($scope.contato);
                }
                if (!angular.equals($scope.contatos, {})) {
                    $scope.contatos.content.push($scope.contato);
                }
            };

            $scope.salvar = function () {
                $scope.contato = Contato.save($scope.contato, function () {
                    notifications.pushForNextRoute('pessoa.salva', 'success', {nome: $scope.contato.pessoa.nome});
                    $scope.pushPessoaListagem();
                    angular.noop($scope.modal ? $scope.modal.close(true) : $state.go('contatos.listar-pessoas'));
                }, function (error) {
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
                $scope.$emit('close.select2');
                $scope.contatoEmpresa = {
                    empresa: {
                        nomeFantasia: name,
                        telefones: [],
                        enderecos: []
                    },
                    tipoContato: $scope.contato.tipoContato
                };
                Contato.save($scope.contatoEmpresa, function (contato) {
                    $scope.contato.pessoa.empresa = contato.empresa;
                    $scope.contato.pessoa.empresa2 = {
                        text: contato.empresa.nomeFantasia,
                        id: angular.toJson(contato.empresa)
                    };
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
