angular.module('lawyer.advogados.edicao', [
    ])

    .config(['$stateProvider',  function config($stateProvider) {
        $stateProvider.state('advogados.editar', {
            url: '/editar/',
            controller: 'AdvogadoEdicaoController',
            templateUrl: 'advogados/editar/advogados.editar.tpl.html'
        });
    }])

    .controller('AdvogadoEdicaoController', ['$scope', 'notifications', '$log', 'Advogado', '$state', '$stateParams',
        function ($scope, notifications, $log, Advogado, $state, $stateParams) {

            $scope.advogado = $state.data;

            if (!$state.data && !$state.advogado) {
                if ($stateParams.uid) {
                    $scope.advogado = Advogado.get({uid : $stateParams.uid});
                } else {
                    $state.go('advogados.listar');
                }
            }

            $scope.salvar = function () {
                $scope.advogado = Advogado.update($scope.advogado, function () {
                    notifications.pushForNextRoute('advogado.alterado', 'success', {nome : $scope.advogado.pessoa.nome});
                    $state.go('advogados.listar');
                });
            };

            $scope.voltar = function () {
                $state.go('advogados.listar');
            };

            $scope.add = function (key) {
                $scope.advogado.pessoa[key].push({});
                //TODO como dar o foco no campo aqui? Com uma directive?
            };

            $scope.remove = function (key, $index) {
                $scope.advogado.pessoa[key].splice($index, 1);
            };

        }])

;