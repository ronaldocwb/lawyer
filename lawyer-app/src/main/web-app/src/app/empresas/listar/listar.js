angular.module('lawyer.empresas.listar', [
    ])

    .config(['$stateProvider',  function config($stateProvider) {
        $stateProvider.state('empresas.listar', {
            url: '/listar',
            controller: 'EmpresaListarController',
            templateUrl: 'empresas/listar/listar.tpl.html'
        });
    }])
.controller('EmpresaListarController', ['$scope', 'i18nNotifications', '$state', '$modal', '$log', 'EmpresaResource',
        function ($scope, i18nNotifications, $state, $modal, $log, EmpresaResource) {

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
