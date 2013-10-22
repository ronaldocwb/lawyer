
angular.module('lawyer.empresas.edicao', [
    ])

    .config(['$stateProvider',  function config($stateProvider) {
        $stateProvider.state('empresas.editar', {
            url: '/editar/',
            controller: 'EmpresaEdicaoController',
            templateUrl: 'contatos/empresas/editar/empresas.editar.tpl.html'
        });
    }])

    .controller('EmpresaEdicaoController', ['$scope', 'notifications', '$log', 'Empresa', '$state', '$stateParams', '$http',
        function ($scope, notifications, $log, Empresa, $state, $stateParams, $http) {

            $scope.empresa = $state.data;

            if ($state.data && $state.data.modal) {
                $scope.modal = $state.data.modal;
            }

            if (!$state.data && !$state.empresa) {
                if ($stateParams.uid) {
                    $scope.empresa = Empresa.get({uid : $stateParams.uid});
                } else {
                    $state.go('empresas.listar');
                }
            }

            $scope.salvar = function () {
                $scope.empresa = Empresa.update({uid : $scope.empresa.uid}, $scope.empresa, function () {
                    notifications.pushForCurrentRoute('empresa.alterada', 'success', {nome : $scope.empresa.nomeFantasia});
                    angular.noop($scope.modal ? $scope.modal.close(true) : $state.go('empresas.listar'));
                });
            };

            $scope.add = function (key) {
                $scope.empresa[key].push({});
            };

            $scope.remove = function (key, $index) {
                $scope.empresa[key].splice($index, 1);
            };

            $scope.voltar = function () {
                angular.noop($scope.modal ? $scope.modal.close(true) : $state.go('empresas.listar'));
            };

            $scope.getMunicipios = function (value) {
                return $http.get('/lawyer/api/municipios?q='+value)
                    .then(function(result){
                        return result.data;
                    });
            };
        }])
;