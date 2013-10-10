
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

    .controller('EmpresaEdicaoController', ['$scope', 'notifications', '$log', 'Empresa', '$state', '$stateParams',
        function ($scope, notifications, $log, Empresa, $state, $stateParams) {

            $scope.empresa = $state.data;

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
                $scope.empresa = new Empresa($scope.empresa);
                $scope.empresa.$update(function () {
                    $log.debug('Empresa alterada:', $scope.empresa);
                    notifications.pushForCurrentRoute('empresa.alterada', 'success', {nome : $scope.empresa.nomeFantasia});
                    $state.go('empresas.listar');
                });

                // teste --> mesma coisa do que está acima, mas sem instanciar diretamente a Empresa e recuperar um $resource
//                $scope.empresa = EmpresaResource.update({uid : $scope.empresa.uid}, $scope.empresa, function () {
//                    $log.debug('Empresa alterada:', $scope.empresa);
//                    $log.debug('Mostrar botao para voltar');
//                });

            };


            $scope.voltar = function () {
                $state.go('empresas.listar');
            };

            $scope.addTelefone = function () {
                $log.debug('Adicionando novo campo de telefone');
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

        }])

;