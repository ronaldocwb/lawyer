angular.module('lawyer.advogados.listar', [
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('advogados.listar', {
            url: '/',
            controller: 'AdvogadoListarController',
            templateUrl: 'advogados/listar/advogados.listar.tpl.html'
        });
    }])
    .controller('AdvogadoListarController', ['$scope', 'notifications', '$state', '$modal', '$log', 'Advogado',
        function ($scope, notifications, $state, $modal, $log, Advogado) {

            $scope.pesquisa = {
                query: '',
                inUse: false,
                hasUsed: false
            };
            $scope.editar = function (advogado) {
                // interrompe a propagacaoo. nao funcionou sem essa parada
                event.preventDefault();
                $state.data = advogado;
                $state.go('advogados.editar');
            };

            $scope.buscar = function () {
                if ($scope.pesquisa.hasUsed === false) {
                    $scope.originalResultSet = angular.copy($scope.advogados);
                    $scope.pesquisa.hasUsed = true;
                }

                $scope.pesquisa.inUse = true;
                if ($scope.pesquisa.query === '') {
                    $scope.pesquisa.inUse = false;
                }
                $scope.advogados = Advogado.get({q: $scope.pesquisa.query});
            };

            $scope.limparBusca = function () {
                $scope.advogados = $scope.originalResultSet;
                $scope.pesquisa.query = '';
                $scope.pesquisa.inUse = false;
            };

            $scope.deletar = function (advogado) {
                var modalInstance = $modal.open({
                    templateUrl: 'advogados/remover/advogados.remover.tpl.html',
                    controller: 'RemoverAdvogadoController',
                    resolve: {
                        advogado: function () {
                            return advogado;
                        }
                    }
                });

                modalInstance.result.then(function () {
                    Advogado.remove({id: advogado.uid}, advogado, function () {
                        notifications.pushForCurrentRoute('advogado.apagada', 'success', {nome : advogado.nome});
                        $scope.advogados.content.splice($scope.advogados.content.indexOf(advogado), 1);
                        if ($scope.originalResultSet) {
                            $scope.originalResultSet.content.splice($scope.originalResultSet.content.indexOf(advogado), 1);
                        }
                    }, function error() {
                        notifications.pushForCurrentRoute('advogado.erro.apagar', 'error', {nome : advogado.nome});
                    });

                }, function () {
                    $log.debug('Modal fechada sem dar OK para deletar.');
                });
            };

            $scope.visualizar = function (advogado) {
                var modalInstance = $modal.open({
                    templateUrl: 'advogados/visualizar/advogados.visualizar.tpl.html',
                    controller: 'VisualizarAdvogadoController',
                    resolve: {
                        advogado: function () {
                            return advogado;
                        }
                    }
                });

                modalInstance.result.then(function (editar) {
                    if (editar === true) {
                        $state.data = advogado;
                        $state.go('advogados.editar');
                    }
                });
            };

            $scope.advogados.current = 1;
            $scope.pageChanged = function (page) {
                $scope.advogados = Advogado.get({q: $scope.pesquisa.inUse ? $scope.pesquisa.query : '', page: page - 1}, function () {
                    $scope.advogados.current = page;
                });
            };
        }])

    .controller('RemoverAdvogadoController', ['$scope', '$modalInstance', 'advogado', function ($scope, $modalInstance, advogado) {
        $scope.advogado = advogado;
        $scope.ok = function () {
            $modalInstance.close();
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }])

    .controller('VisualizarAdvogadoController', ['$scope', '$modalInstance', 'advogado', function ($scope, $modalInstance, advogado) {
        $scope.advogado = advogado;
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
