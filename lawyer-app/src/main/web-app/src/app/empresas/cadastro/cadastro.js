angular.module('lawyer.empresas.cadastro', [
        'ui.mask'
    ])

    .config(['$stateProvider',  function config($stateProvider) {
        $stateProvider.state('empresas.cadastro', {
            url: '/cadastro',
            controller: 'EmpresaCadastroController',
            templateUrl: 'empresas/cadastro/cadastro.tpl.html'
        });
    }])

    .controller('EmpresaCadastroController', ['$scope', 'i18nNotifications', '$log', 'EmpresaResource',
        function ($scope, i18nNotifications, $log, EmpresaResource) {

            $scope.empresa = {
                telefones : [],
                enderecos : []
            };

            $scope.cadastrar = function () {
                $log.debug('Enviando cadastro para o endpoint', $scope.empresa);
                $scope.result = EmpresaResource.save({}, $scope.empresa, function () {
                    $log.debug('Empresa cadastrada:', $scope.result);
                    $log.debug('Mostrar botao para voltar');
                });

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