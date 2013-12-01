
angular.module('lawyer.areasAtuacao.edicao', [
    ])

    .config(['$stateProvider',  function config($stateProvider) {
        $stateProvider.state('areasAtuacao.edicao', {
            url: '/edicao',
            controller: 'AreaAtuacaoEdicaoController',
            templateUrl: 'areasAtuacao/cadastro/atuacao.cadastro.tpl.html'
        });
    }])

    .controller('AreaAtuacaoEdicaoController', ['$scope', 'notifications', 'AreaAtuacao', '$state',
        function ($scope, notifications, AreaAtuacao, $state) {

            if (!$state.data) {
                $state.transitionTo('areasAtuacao');
            }

            $scope.areaAtuacao = $state.data;

            $scope.salvar = function () {
                $scope.result = AreaAtuacao.update($scope.areaAtuacao, function () {
                    notifications.pushForNextRoute('areaAtuacao.salva', 'success', {nome : $scope.areaAtuacao.nome});
                    $state.transitionTo('areasAtuacao.listar');
                }, function () {
                    notifications.pushForCurrentRoute('areaAtuacao.erro.salvar', 'error', {nome : $scope.areaAtuacao.nome});
                });
            };

            $scope.voltar = function () {
                $state.go('areasAtuacao.listar');
            };


        }])

;