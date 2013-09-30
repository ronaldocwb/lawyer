
angular.module('lawyer.empresas.edicao', [
        'ui.mask'
    ])

    .config(['$stateProvider',  function config($stateProvider) {
        $stateProvider.state('empresas.edicao', {
            url: '/edicao',
            controller: 'EmpresaEdicaoController',
            templateUrl: 'empresas/edicao/edicao.tpl.html'
        });
    }])

    .controller('EmpresaEdicaoController', ['$scope', 'i18nNotifications', '$log', 'EmpresaResource', '$state',
        function ($scope, i18nNotifications, $log, EmpresaResource, $state) {

            // $state não possui a empresa para alterar. Volta pra pagina anterior.
            if (!$state.data) {
                $state.transitionTo('empresas');
            }

            $scope.empresa = $state.data;

            $scope.salvar = function () {
                $log.debug('Enviando cadastro para o endpoint', $scope.empresa);
                $scope.result = EmpresaResource.update({id : $scope.empresa.uid}, $scope.empresa, function () {
                    $log.debug('Empresa alterada:', $scope.result);
                    $log.debug('Mostrar botao para voltar');
                });

            };

            $scope.addTelefone = $scope.empresa.telefones.push({});
            $scope.addEndereco = $scope.empresa.enderecos.push({});

            $scope.removerTelefone = function (telefone) {
                $log.debug('removendo o telefone', telefone);
                $scope.empresa.telefones.splice($scope.empresa.telefones.indexOf(telefone), 1);
            };

            $scope.removerEndereco = function (endereco) {
                $log.debug('removendo o endereco', endereco);
                $scope.empresa.enderecos.splice($scope.empresa.enderecos.indexOf(endereco), 1);
            };

        }])
;