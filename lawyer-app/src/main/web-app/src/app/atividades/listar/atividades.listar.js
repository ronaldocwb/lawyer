angular.module('lawyer.atividades.listar', [
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('atividades.listar', {
            url: '/',
            controller: 'ListarAtividadesController',
            templateUrl: 'atividades/listar/atividades.listar.tpl.html'
        });
    }])
    .controller('ListarAtividadesController', ['$scope', 'notifications', '$state', '$modal', '$log', 'Empresa',
        function ($scope, notifications, $state, $modal, $log, Empresa) {

            $scope.pesquisa = {
                query: '',
                inUse: false,
                hasUsed: false
            };
            $scope.editar = function (atividades, $event) {
                $event.preventDefault();
                $state.data = atividades;
                $state.go('atividades.editar');
            };

            $scope.buscar = function () {
                if ($scope.pesquisa.hasUsed === false) {
                    $scope.originalResultSet = angular.copy($scope.atividades);
                    $scope.pesquisa.hasUsed = true;
                }

                $scope.pesquisa.inUse = true;
                if ($scope.pesquisa.query === '') {
                    $scope.pesquisa.inUse = false;
                }
                $scope.atividades = Empresa.get({q: $scope.pesquisa.query});
            };

            $scope.limparBusca = function () {
                $scope.atividades = $scope.originalResultSet;
                $scope.pesquisa.query = '';
                $scope.pesquisa.inUse = false;
            };

            $scope.deletar = function (atividades) {
                var modalInstance = $modal.open({
                    templateUrl: 'contatos/atividades/remover/atividades.remover.tpl.html',
                    controller: 'RemoverEmpresaController',
                    resolve: {
                        atividades: function () {
                            return atividades;
                        }
                    }
                });

                modalInstance.result.then(function () {
                    Empresa.remove(atividades, function () {
                        notifications.pushForCurrentRoute('atividades.apagada', 'success', {nome : atividades.nomeFantasia});
                        $scope.atividades.content.splice($scope.atividades.content.indexOf(atividades), 1);
                        if ($scope.originalResultSet) {
                            $scope.originalResultSet.content.splice($scope.originalResultSet.content.indexOf(atividades), 1);
                        }
                    }, function error() {
                        notifications.pushForCurrentRoute('atividades.erro.apagar', 'error', {nome : atividades.nomeFantasia});
                    });

                }, function () {
                    $log.info('Modal fechada sem dar OK.');
                });
            };

            $scope.visualizar = function (atividades) {
                var modalInstance = $modal.open({
                    templateUrl: 'atividades/visualizar/atividades.visualizar.tpl.html',
                    controller: 'VisualizarEmpresaController',
                    resolve: {
                        atividades: function () {
                            return atividades;
                        }
                    }
                });

                modalInstance.result.then(function (editar) {
                    if (editar === true) {
                        $state.data = atividades;
                        $state.go('atividades.editar');
                    }
                });
            };

            $scope.atividades.current = 1;
            $scope.pageChanged = function (page) {
                $scope.atividades = Empresa.get({q: $scope.pesquisa.inUse ? $scope.pesquisa.query : '', page: page - 1}, function () {
                    $scope.atividades.current = page;
                });
            };
        }])

    .controller('RemoverEmpresaController', ['$scope', '$modalInstance', 'atividades', function ($scope, $modalInstance, atividades) {
        $scope.atividades = atividades;
        $scope.ok = function () {
            $modalInstance.close();
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }])

    .controller('VisualizarEmpresaController', ['$scope', '$modalInstance', 'atividades', function ($scope, $modalInstance, atividades) {
        $scope.atividades = atividades;
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
