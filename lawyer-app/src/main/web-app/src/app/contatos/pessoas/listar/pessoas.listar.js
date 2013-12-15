angular.module('lawyer.pessoas.listar', [
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('contatos.listar-pessoas', {
            url: '/pessoas/',
            controller: 'ListarPessoasController',
            templateUrl: 'contatos/pessoas/listar/pessoas.listar.tpl.html'
        });
    }])
    .controller('ListarPessoasController', ['$scope', 'notifications', '$state', '$modal', '$log', 'Contato',
        function ($scope, notifications, $state, $modal, $log, Contato) {

            if (angular.equals($scope.pessoas, {})) {
                $scope.fetchPessoas();
            }

            $scope.pesquisa = {
                query: '',
                inUse: false,
                hasUsed: false
            };

            $scope.editar = function (contato,$event) {
                $event.preventDefault();
                $state.data = contato;
                $state.go('contatos.editar-pessoa');
            };

            $scope.buscar = function () {
                if ($scope.pesquisa.hasUsed === false) {
                    $scope.originalResultSet = angular.copy($scope.pessoas);
                    $scope.pesquisa.hasUsed = true;
                }

                $scope.pesquisa.inUse = true;
                if ($scope.pesquisa.query === '') {
                    $scope.pesquisa.inUse = false;
                }
                $scope.pessoas = Contato.getPessoas({q: $scope.pesquisa.query});
            };

            $scope.limparBusca = function () {
                $scope.pessoas = $scope.originalResultSet;
                $scope.pesquisa.query = '';
                $scope.pesquisa.inUse = false;
            };

            $scope.deletar = function (contato) {
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
                        $scope.pessoas.content.splice($scope.pessoas.content.indexOf(contato), 1);
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

            $scope.visualizar = function (contato) {
                var modalInstance = $modal.open({
                    templateUrl: 'contatos/pessoas/visualizar/pessoas.visualizar.tpl.html',
                    controller: 'VisualizarPessoaController',
                    resolve: {
                        contato: function () {
                            return contato;
                        }
                    }
                });

                modalInstance.result.then(function (editar) {
                    if (editar === true) {
                        $state.data = contato;
                        $state.go('contatos.editar-pessoa');
                    }
                });
            };

            $scope.pessoas.current = 1;
            $scope.pageChanged = function (page) {
                $scope.pessoas = Contato.getPessoas({q: $scope.pesquisa.inUse ? $scope.pesquisa.query : '', page: page - 1}, function () {
                    $scope.pessoas.current = page;
                });
            };
        }])

    .controller('RemoverPessoaController', ['$scope', '$modalInstance', 'contato', function ($scope, $modalInstance, contato) {
        $scope.pessoa = contato.pessoa;
        $scope.ok = function () {
            $modalInstance.close();
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }])

    .controller('VisualizarPessoaController', ['$scope', '$modalInstance', 'contato', function ($scope, $modalInstance, contato) {
        $scope.pessoa = contato.pessoa;
        $scope.ok = function () {
            $modalInstance.close(false);
        };
        $scope.editar = function () {
            $modalInstance.close(true);
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }])

;
