angular.module('lawyer.areasAtuacao.listar', [
    ])

    .config(['$stateProvider',  function config($stateProvider) {
        $stateProvider.state('areasAtuacao.listar', {
            url: '/listar',
            controller: 'AreaAtuacaoListarController',
            templateUrl: 'areasAtuacao/listar/listar.tpl.html'
        });
    }])
.controller('AreaAtuacaoListarController', ['$scope', 'i18nNotifications', '$state', '$modal', '$log', 'AreaAtuacaoResource',
        function ($scope, i18nNotifications, $state, $modal, $log, AreaAtuacaoResource) {

            $scope.editAreaAtuacao = function (areaAtuacao) {
                // interrompe a propagacaoo. nao funcionou sem essa parada
                event.preventDefault();
                $state.data = areaAtuacao;
                // vai para a rota de edicao.
                $state.transitionTo('areasAtuacao.edicao');

            };

            $scope.deleteAreaAtuacao = function (areaAtuacao) {
                var modalInstance = $modal.open({
                    templateUrl: 'areasAtuacao/remocao/remover.tpl.html',
                    controller: 'RemoverAreaAtuacaoController',
                    resolve: {
                        areaAtuacao: function () {
                            return areaAtuacao;
                        }
                    }
                });

                modalInstance.result.then(function (areaAtuacao) {
                    $log.warn('Removendo Área de Atuação!');
                    $log.debug(areaAtuacao);
                    AreaAtuacaoResource.delete({id: areaAtuacao.uid}, function () {

                        $log.debug('Area de Atuacao apagada', areaAtuacao.uid);
                        $scope.areasAtuacao.content.splice($scope.areasAtuacao.content.indexOf(areaAtuacao), 1);

                    }, function error(err) {
                        $log.debug('Area de Atuacao nao apagada por erro no server', err);
                        $log.debug('Area de Atuacao deve ter chaves estrangeiras que nao foram apagadas corretamente');
                    });

                }, function () {
                    $log.info('Modal fechada sem dar OK.');
                });
            };

            $scope.areasAtuacao.current = 1;
            $scope.pageChanged = function (page) {
                $scope.areasAtuacao = AreaAtuacaoResource.query({page : page-1}, function () {
                    $scope.areasAtuacao.current = page;
                });
            };
        }])

    .controller('RemoverAreaAtuacaoController', ['$scope', '$modalInstance', 'areaAtuacao', function ($scope, $modalInstance, areaAtuacao) {
        $scope.areaAtuacao = areaAtuacao;
        $scope.ok = function () {
            $modalInstance.close($scope.areaAtuacao);
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }])

;
