angular.module('lawyer.assuntos.listar', [
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('assuntos.listar', {
            url: '/',
            controller: 'ListarAssuntosController',
            templateUrl: 'assuntos/listar/assuntos.listar.tpl.html'
        });
    }])
    .controller('ListarAssuntosController', ['$scope', 'notifications', '$state', '$modal', '$log', 'Assunto',
        function ($scope, notifications, $state, $modal, $log, Assunto) {

            $scope.pesquisa = {
                query: '',
                inUse: false,
                hasUsed: false
            };
            $scope.editar = function (assuntos, $event) {
                $event.preventDefault();
                $state.data = assuntos;
                $state.go('assuntos.editar');
            };

            $scope.buscar = function () {
                if ($scope.pesquisa.hasUsed === false) {
                    $scope.originalResultSet = angular.copy($scope.assuntos);
                    $scope.pesquisa.hasUsed = true;
                }


                $scope.pesquisa.inUse = $scope.pesquisa.query !== '';
                $scope.assuntos = Assunto.get({q: $scope.pesquisa.query});
            };

            $scope.limparBusca = function () {
                $scope.assuntos = $scope.originalResultSet;
                $scope.pesquisa.query = '';
                $scope.pesquisa.inUse = false;
            };

            $scope.deletar = function (assuntos) {
                var modalInstance = $modal.open({
                    templateUrl: 'assuntos/remover/assuntos.remover.tpl.html',
                    controller: 'RemoverAssuntoController',
                    resolve: {
                        assuntos: function () {
                            return assuntos;
                        }
                    }
                });

                modalInstance.result.then(function () {
                    Assunto.remove(assuntos, function () {
                        notifications.pushForCurrentRoute('atividade.apagada', 'success', {nome : assuntos.nomeFantasia});
                        $scope.assuntos.content.splice($scope.assuntos.content.indexOf(assuntos), 1);
                        if ($scope.originalResultSet) {
                            $scope.originalResultSet.content.splice($scope.originalResultSet.content.indexOf(assuntos), 1);
                        }
                    }, function error() {
                        notifications.pushForCurrentRoute('atividade.erro.apagar', 'error', {nome : assuntos.nomeFantasia});
                    });

                });
            };

            $scope.visualizar = function (assuntos) {
                var modalInstance = $modal.open({
                    templateUrl: 'assuntos/visualizar/assuntos.visualizar.tpl.html',
                    controller: 'VisualizarAssuntoController',
                    resolve: {
                        assuntos: function () {
                            return assuntos;
                        }
                    }
                });

                modalInstance.result.then(function (editar) {
                    if (editar === true) {
                        $state.data = assuntos;
                        $state.go('assuntos.editar');
                    }
                });
            };

            $scope.assuntos.current = 1;
            $scope.pageChanged = function (page) {
                $scope.assuntos = Assunto.get({q: $scope.pesquisa.inUse ? $scope.pesquisa.query : '', page: page - 1}, function () {
                    $scope.assuntos.current = page;
                });
            };
        }])

    .controller('RemoverAssuntoController', ['$scope', '$modalInstance', 'assuntos', function ($scope, $modalInstance, assuntos) {
        $scope.assuntos = assuntos;
        $scope.ok = function () {
            $modalInstance.close();
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }])

    .controller('VisualizarAssuntoController', ['$scope', '$modalInstance', 'assuntos', function ($scope, $modalInstance, assuntos) {
        $scope.assuntos = assuntos;
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
