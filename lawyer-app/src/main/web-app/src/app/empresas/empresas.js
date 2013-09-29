angular.module('lawyer.empresas', [
        'EmpresaResource'
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('empresas', {
            url: '/empresas',
            views: {
                "main": {
                    controller: 'EmpresaController',
                    templateUrl: 'empresas/empresas.tpl.html'
                }
            },
            // Recupera a lista de empresas. Após o término do promise, constrói a view e o controller.
            // empresas deverá ser injetado como parâmetro no construtor do controller.
            resolve: {
                empresas: function (EmpresaResource) {
                    return EmpresaResource.query();
                }
            }
        });
    }])

    .controller('EmpresaController', ['$scope', 'i18nNotifications', '$state', 'empresas', '$modal', '$log', 'EmpresaResource',
        function ($scope, i18nNotifications, $state, empresas, $modal, $log, EmpresaResource) {
            $scope.empresas = empresas;

            $scope.editEmpresa = function (empresa) {
                event.preventDefault();
                $state.data = empresa;
                $state.transitionTo('empresas.edicao');

            };

            $scope.deleteEmpresa = function (empresa) {
                var modalInstance = $modal.open({
                    templateUrl: 'empresas/remocao/remover.tpl.html',
                    controller: 'RemoverEmpresaController',
                    resolve: {
                        empresa: function () {
                            return empresa;
                        }
                    }
                });

                modalInstance.result.then(function (empresa) {
                    $log.warn('Removendo empresa!');
                    $log.debug(empresa);
                    EmpresaResource.delete({id: empresa.uid}, function () {

                        $log.debug('Empresa apagada', empresa.uid);
                        $scope.empresas.content.splice($scope.empresas.content.indexOf(empresa), 1);

                    }, function error(err) {
                        $log.debug('Empresa nao apagada por erro no server', err);
                        $log.debug('Empresa deve ter chaves estrangeiras que nao foram apagadas corretamente');
                    });

                }, function () {
                    $log.info('Modal fechada sem dar OK.');
                });
            };
        }])
    .controller('RemoverEmpresaController', ['$scope', '$modalInstance', 'empresa', function ($scope, $modalInstance, empresa) {
        $scope.empresa = empresa;
        $scope.ok = function () {
            $modalInstance.close($scope.empresa);
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }])

;
