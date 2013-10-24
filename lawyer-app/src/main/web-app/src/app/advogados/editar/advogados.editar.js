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

            $scope.pessoa = $state.data;

            if (!$state.data && !$state.pessoa) {
                if ($stateParams.uid) {
                    $scope.pessoa = Advogado.get({uid : $stateParams.uid});
                } else {
                    $state.go('advogados.listar');
                }
            }

            $scope.salvar = function () {
                $scope.pessoa = Advogado.update($scope.pessoa, function () {
                    notifications.pushForNextRoute('pessoa.alterada', 'success', {nome : $scope.pessoa.nome});
                    $state.go('advogados.listar');
                });
            };

            $scope.voltar = function () {
                $state.go('advogados.listar');
            };

            $scope.add = function (key) {
                $scope.pessoa[key].push({});
            };

            $scope.remove = function (key, $index) {
                $scope.pessoa[key].splice($index, 1);
            };

        }])

;