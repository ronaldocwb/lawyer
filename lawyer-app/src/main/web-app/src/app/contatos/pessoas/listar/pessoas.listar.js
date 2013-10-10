angular.module('lawyer.pessoas.listar', [
    ])

    .config(['$stateProvider',  function config($stateProvider) {
        $stateProvider.state('pessoas.listar', {
            url: '/',
            controller: 'PessoaListarController',
            templateUrl: 'contatos/pessoas/listar/pessoas.listar.tpl.html'
        });
    }])
.controller('PessoaListarController', ['$scope', 'notifications', '$state', '$modal', '$log', 'Pessoa',
        function ($scope, notifications, $state, $modal, $log, Pessoa) {

            $scope.pesquisa =  {
                query : '',
                inUse : false,
                hasUsed : false
            };
            $scope.editar = function (empresa) {
                // interrompe a propagacaoo. nao funcionou sem essa parada
                event.preventDefault();
                $state.data = empresa;
                // vai para a rota de edicao.
                $state.go('pessoas.editar');
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
                $scope.pessoas = Pessoa.get({q : $scope.pesquisa.query});
            };

            $scope.limparBusca = function () {
                $scope.pessoas = $scope.originalResultSet;
                $scope.pesquisa.query = '';
                $scope.pesquisa.inUse = false;
            };

            $scope.deletar = function (empresa) {
                var modalInstance = $modal.open({
                    templateUrl: 'contatos/pessoas/remover/pessoas.remover.tpl.html',
                    controller: 'RemoverPessoaController',
                    resolve: {
                        empresa: function () {
                            return empresa;
                        }
                    }
                });

                modalInstance.result.then(function () {
                    $log.warn('Removendo empresa!');
                        Pessoa.remove({id: empresa.uid}, empresa, function () {
                        $log.debug('Pessoa apagada', empresa.uid);
                        $scope.pessoas.content.splice($scope.pessoas.content.indexOf(empresa), 1);
                        if ($scope.originalResultSet) {
                            $scope.originalResultSet.content.splice($scope.originalResultSet.content.indexOf(empresa), 1);
                        }
                    }, function error(err) {
                        $log.debug('Pessoa nao apagada por erro no server', err);
                        $log.debug('Pessoa deve ter chaves estrangeiras que nao foram apagadas corretamente');
                    });

                }, function () {
                    $log.info('Modal fechada sem dar OK.');
                });
            };

            $scope.visualizar = function (empresa) {
                var modalInstance = $modal.open({
                    templateUrl: 'contatos/pessoas/visualizar/pessoas.visualizar.tpl.html',
                    controller: 'VisualizarPessoaController',
                    resolve: {
                        empresa: function () {
                            return empresa;
                        }
                    }
                });

                modalInstance.result.then(function (editar) {
                    if (editar === true) {
                        $state.data = empresa;
                        $state.go('pessoas.editar');
                    }
                });
            };

            $scope.pessoas.current = 1;
            $scope.pageChanged = function (page) {
                $scope.pessoas = Pessoa.get({q : $scope.pesquisa.inUse ? $scope.pesquisa.query : '', page : page-1}, function () {
                    $scope.pessoas.current = page;
                });
            };
        }])

    .controller('RemoverPessoaController', ['$scope', '$modalInstance', 'empresa', function ($scope, $modalInstance, empresa) {
        $scope.empresa = empresa;
        $scope.ok = function () {
            $modalInstance.close();
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }])

    .controller('VisualizarPessoaController', ['$scope', '$modalInstance', 'empresa', function ($scope, $modalInstance, empresa) {
        $scope.empresa = empresa;
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
