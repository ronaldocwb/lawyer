angular.module('lawyer.empresas.listar', [
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('contatos.listar-empresas', {
            url: '/empresas/',
            controller: 'ListarEmpresaController',
            templateUrl: 'contatos/empresas/listar/empresas.listar.tpl.html'
        });
    }])
    .controller('ListarEmpresaController', ['$scope', 'notifications', '$state', '$modal', '$log', 'Contato',
        function ($scope, notifications, $state, $modal, $log, Contato) {

            if (angular.equals($scope.empresas, {})) {
                $scope.fetchEmpresas();
            }
            
            $scope.pesquisa = {
                query: '',
                inUse: false,
                hasUsed: false
            };
            $scope.editar = function (contato, $event) {
                $event.preventDefault();
                $state.data = contato;
                $state.go('contatos.editar-empresa');
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
                $scope.empresas = Contato.getEmpresas({q: $scope.pesquisa.query});
            };

            $scope.limparBusca = function () {
                $scope.empresas = $scope.originalResultSet;
                $scope.pesquisa.query = '';
                $scope.pesquisa.inUse = false;
            };

            $scope.deletar = function (contato) {
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
                    Contato.remove({uid: contato.uid}, function () {
                        notifications.pushForCurrentRoute('empresa.apagada', 'information', {nome : contato.empresa.nomeFantasia});
                        $scope.empresas.content.splice($scope.empresas.content.indexOf(contato.empresa), 1);
                        if ($scope.originalResultSet) {
                            $scope.originalResultSet.content.splice($scope.originalResultSet.content.indexOf(contato.empresa), 1);
                        }
                    }, function error() {
                        notifications.pushForCurrentRoute('empresa.erro.apagar', 'error', {nome : contato.empresa.nomeFantasia});
                    });

                }, function () {
                    $log.info('Modal fechada sem dar OK.');
                });
            };

            $scope.visualizar = function (contato) {
                var modalInstance = $modal.open({
                    templateUrl: 'contatos/empresas/visualizar/empresas.visualizar.tpl.html',
                    controller: 'VisualizarEmpresaController',
                    resolve: {
                        empresa: function () {
                            return contato.empresa;
                        }
                    }
                });

                modalInstance.result.then(function (editar) {
                    if (editar === true) {
                        $state.data = contato;
                        $state.go('contatos.editar-empresa');
                    }
                });
            };

            $scope.empresas.current = 1;
            $scope.pageChanged = function (page) {
                $scope.empresas = Contato.getEmpresas({q: $scope.pesquisa.inUse ? $scope.pesquisa.query : '', page: page - 1}, function () {
                    $scope.empresas.current = page;
                });
            };
        }])

    .controller('RemoverEmpresaController', ['$scope', '$modalInstance', 'contato', function ($scope, $modalInstance, contato) {
        $scope.empresa = contato.empresa;
        $scope.ok = function () {
            $modalInstance.close();
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }])

    .controller('VisualizarEmpresaController', ['$scope', '$modalInstance', 'contato', function ($scope, $modalInstance, contato) {
        $scope.contato = contato.empresa;
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
