
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
            $scope.modal = $state.data.modal;

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
                    if ($scope.modal) {
                        $scope.modal.close(true);
                    } else {
                        notifications.pushForCurrentRoute('empresa.alterada', 'success', {nome : $scope.empresa.nomeFantasia});
                        $state.go('empresas.listar');
                    }
                });
            };

            $scope.voltar = function () {
                $state.go('empresas.listar');
            };

            $scope.addTelefone = function () {
                $log.debug('Adicionando novo campo de telefone');
                console.log($scope.empresa);
                $scope.empresa.telefones.push({});
                $log.debug('telefones: ', $scope.empresa.telefones);
            };

            $scope.removerTelefone = function (telefone) {
                $log.debug('removendo o telefone', telefone);
                $scope.empresa.telefones.splice($scope.empresa.telefones.indexOf(telefone), 1);
            };

            $scope.addEndereco = function () {
                $log.debug('Adicionando novo campo de endereco');
                $scope.empresa.enderecos.push({});
                $log.debug('telefones: ', $scope.empresa.enderecos);
            };

            $scope.removerEndereco = function (endereco) {
                $log.debug('removendo o endereco', endereco);
                $scope.empresa.enderecos.splice($scope.empresa.enderecos.indexOf(endereco), 1);
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