angular.module('lawyer.areasAtuacao.listar', [
    ])

    .config(['$stateProvider',  function config($stateProvider) {
        $stateProvider.state('areasAtuacao.listar', {
            url: '/',
            controller: 'AreaAtuacaoListarController',
            templateUrl: 'areasAtuacao/listar/atuacao.listar.tpl.html'
        });
    }])
.controller('AreaAtuacaoListarController', ['$scope', 'notifications', '$state', '$modal', '$log', 'AreaAtuacao',
        function ($scope, notifications, $state, $modal, $log, AreaAtuacao) {

            $scope.editAreaAtuacao = function (areaAtuacao) {
                event.preventDefault();
                $state.data = areaAtuacao;
                $state.transitionTo('areasAtuacao.edicao');
            };

            $scope.deleteAreaAtuacao = function (areaAtuacao) {
                var modalInstance = $modal.open({
                    templateUrl: 'areasAtuacao/remocao/atuacao.remover.tpl.html',
                    controller: 'RemoverAreaAtuacaoController',
                    resolve: {
                        areaAtuacao: function () {
                            return areaAtuacao;
                        }
                    }
                });

                modalInstance.result.then(function (areaAtuacao) {
                    AreaAtuacao.delete(areaAtuacao, function () {
                        $scope.areasAtuacao.content.splice($scope.areasAtuacao.content.indexOf(areaAtuacao), 1);

                    }, function error() {
                        notifications.pushForNextRoute('areaAtuacao.erro.apagar', 'error');
                    });

                });
            };

            $scope.areasAtuacao.current = 1;
            $scope.pageChanged = function (page) {
                $scope.areasAtuacao = AreaAtuacao.query({page : page-1}, function () {
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
