angular.module('lawyer.empresas.listar', [
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('empresas.listar', {
            url: '/',
            controller: 'EmpresaListarController',
            templateUrl: 'contatos/empresas/listar/empresas.listar.tpl.html'
        });
    }])
    .controller('EmpresaListarController', ['$scope', 'notifications', '$state', '$modal', '$log', 'Empresa',
        function ($scope, notifications, $state, $modal, $log, Empresa) {

            $scope.pesquisa = {
                query: '',
                inUse: false,
                hasUsed: false
            };
            $scope.editar = function (empresa) {
                // interrompe a propagacaoo. nao funcionou sem essa parada
                event.preventDefault();
                $state.data = empresa;
                $state.go('empresas.editar');
            };

            $scope.buscar = function () {
                if ($scope.pesquisa.hasUsed === false) {
                    $scope.originalResultSet = angular.copy($scope.empresas);
                    $scope.pesquisa.hasUsed = true;
                }

                $scope.pesquisa.inUse = true;
                if ($scope.pesquisa.query === '') {
                    $scope.pesquisa.inUse = false;
                }
                $scope.empresas = Empresa.get({q: $scope.pesquisa.query});
            };

            $scope.limparBusca = function () {
                $scope.empresas = $scope.originalResultSet;
                $scope.pesquisa.query = '';
                $scope.pesquisa.inUse = false;
            };

            $scope.deletar = function (empresa) {
                var modalInstance = $modal.open({
                    templateUrl: 'contatos/empresas/remover/empresas.remover.tpl.html',
                    controller: 'RemoverEmpresaController',
                    resolve: {
                        empresa: function () {
                            return empresa;
                        }
                    }
                });

                modalInstance.result.then(function () {
                    Empresa.remove({id: empresa.uid}, empresa, function () {
                        notifications.pushForCurrentRoute('empresa.apagada', 'success', {nome : empresa.nomeFantasia});
                        $scope.empresas.content.splice($scope.empresas.content.indexOf(empresa), 1);
                        if ($scope.originalResultSet) {
                            $scope.originalResultSet.content.splice($scope.originalResultSet.content.indexOf(empresa), 1);
                        }
                    }, function error() {
                        notifications.pushForCurrentRoute('empresa.erro.apagar', 'error', {nome : empresa.nomeFantasia});
                    });

                }, function () {
                    $log.info('Modal fechada sem dar OK.');
                });
            };

            $scope.visualizar = function (empresa) {
                var modalInstance = $modal.open({
                    templateUrl: 'contatos/empresas/visualizar/empresas.visualizar.tpl.html',
                    controller: 'VisualizarEmpresaController',
                    resolve: {
                        empresa: function () {
                            return empresa;
                        }
                    }
                });

                modalInstance.result.then(function (editar) {
                    if (editar === true) {
                        $state.data = empresa;
                        $state.go('empresas.editar');
                    }
                });
            };

            $scope.empresas.current = 1;
            $scope.pageChanged = function (page) {
                $scope.empresas = Empresa.get({q: $scope.pesquisa.inUse ? $scope.pesquisa.query : '', page: page - 1}, function () {
                    $scope.empresas.current = page;
                });
            };
        }])

    .controller('RemoverEmpresaController', ['$scope', '$modalInstance', 'empresa', function ($scope, $modalInstance, empresa) {
        $scope.empresa = empresa;
        $scope.ok = function () {
            $modalInstance.close();
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }])

    .controller('VisualizarEmpresaController', ['$scope', '$modalInstance', 'empresa', function ($scope, $modalInstance, empresa) {
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
