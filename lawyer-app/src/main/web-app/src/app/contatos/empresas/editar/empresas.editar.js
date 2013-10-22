
angular.module('lawyer.empresas.edicao', [
        'ui.mask'
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
            $scope.modal = null;
            if ($state.data && $state.data.modal) {
                $scope.modal = $state.data.modal;
            }

            // $state não possui a empresa para alterar. Volta pra pagina anterior.
            if (!$state.data && !$state.empresa) {
                if ($stateParams.uid) {
                    $scope.empresa = Empresa.get({uid : $stateParams.uid});
                } else {
                    $state.go('empresas.listar');
                }
            }

            $scope.salvar = function () {
                $log.debug('Enviando cadastro para o endpoint', $scope.empresa);

                $scope.empresa = Empresa.update({uid : $scope.empresa.uid}, $scope.empresa, function () {
                    $log.debug('Empresa alterada:', $scope.empresa);
                    notifications.pushForCurrentRoute('empresa.alterada', 'success', {nome : $scope.empresa.nomeFantasia});
                    if ($scope.modal) {
                        $scope.modal.close(true);
                    } else {
                        $state.go('empresas.listar');
                    }
                });
            };

            $scope.voltar = function () {
                $state.go('empresas.listar');
            };

            $scope.add = function (key) {
                $scope.empresa[key].push({});
            };

            $scope.remove = function (key, $index) {
                $scope.empresa[key].splice($index, 1);
            };


            $scope.voltar = function () {
                if ($scope.modal) {
                    $scope.modal.close(true);
                } else {
                    $state.go("empresas.listar");
                }
            };

            $scope.getMunicipios = function (value) {
                return $http.get('/lawyer/api/municipios?q='+value)
                    .then(function(results){
                        return results.data;
                    });
            };
        }])
;