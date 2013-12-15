angular.module('lawyer.contatos.listar', [
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('contatos.listar-todos', {
            url: '/',
            controller: 'ListarContatosController',
            templateUrl: 'contatos/listar/contatos.listar.tpl.html'
        });
    }])
    .controller('ListarContatosController', ['$scope', 'notifications', '$state', '$modal', '$log', 'Contato',
        function ($scope, notifications, $state, $modal, $log, Contato) {

            if (angular.equals($scope.contatos, {})) {
                $scope.fetchContatos();
            }

            $scope.pesquisa = {
                query: '',
                inUse: false,
                hasUsed: false
            };
            $scope.editarPessoa = function (contato ,$event) {
                $event.preventDefault();
                $state.data = contato;
                $state.go('contatos.editar-pessoa');
            };

            $scope.editarEmpresa = function (contato ,$event) {
                $event.preventDefault();
                $state.data = contato;
                $state.go('contatos.editar-empresa');
            };

            $scope.buscar = function () {
                if ($scope.pesquisa.hasUsed === false) {
                    $scope.originalResultSet = angular.copy($scope.contatos);
                    $scope.pesquisa.hasUsed = true;
                }

                $scope.pesquisa.inUse = true;
                if ($scope.pesquisa.query === '') {
                    $scope.pesquisa.inUse = false;
                }
                $scope.contatos = Contato.getPessoas({q: $scope.pesquisa.query});
            };

            $scope.limparBusca = function () {
                $scope.contatos = $scope.originalResultSet;
                $scope.pesquisa.query = '';
                $scope.pesquisa.inUse = false;
            };

            $scope.contatos.current = 1;
            $scope.pageChanged = function (page) {
                $scope.contatos = Contato.getPessoas({q: $scope.pesquisa.inUse ? $scope.pesquisa.query : '', page: page - 1}, function () {
                    $scope.contatos.current = page;
                });
            };

            $scope.deletarPessoa = function (contato) {
                var modalInstance = $modal.open({
                    templateUrl: 'contatos/pessoas/remover/pessoas.remover.tpl.html',
                    controller: 'RemoverPessoaController',
                    resolve: {
                        contato: function () {
                            return contato;
                        }
                    }
                });

                modalInstance.result.then(function () {
                    Contato.remove({uid: contato.uid}, function () {
                        notifications.pushForCurrentRoute('pessoa.apagada', 'information', {nome : contato.pessoa.nome});
                        $scope.contatos.content.splice($scope.contatos.content.indexOf(contato), 1);
                        if ($scope.originalResultSet) {
                            $scope.originalResultSet.content.splice($scope.originalResultSet.content.indexOf(contato), 1);
                        }
                    }, function error() {
                        notifications.pushForCurrentRoute('pessoa.erro.apagar', 'error', {nome : contato.pessoa.nome});
                    });

                }, function () {
                    $log.debug('Modal fechada sem dar OK para deletar.');
                });
            };

            $scope.deletarEmpresa = function (contato) {
                var modalInstance = $modal.open({
                    templateUrl: 'contatos/empresas/remover/empresas.remover.tpl.html',
                    controller: 'RemoverEmpresaController',
                    resolve: {
                        contato: function () {
                            return contato;
                        }
                    }
                });

                modalInstance.result.then(function () {
                    Contato.remove({uid : contato.uid}, function () {
                        notifications.pushForCurrentRoute('empresa.apagada', 'information', {nome : contato.empresa.nomeFantasia});
                        $scope.contatos.content.splice($scope.contatos.content.indexOf(contato), 1);
                        if ($scope.originalResultSet) {
                            $scope.originalResultSet.content.splice($scope.originalResultSet.content.indexOf(contato), 1);
                        }
                    }, function error() {
                        notifications.pushForCurrentRoute('empresa.erro.apagar', 'error', {nome : contato.empresa.nomeFantasia});
                    });

                }, function () {
                    $log.info('Modal fechada sem dar OK.');
                });
            };
        }])
;

