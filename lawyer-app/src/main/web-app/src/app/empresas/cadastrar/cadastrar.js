angular.module('lawyer.empresas.cadastro', [
        'ui.mask'
    ])

    .config(['$stateProvider',  function config($stateProvider) {
        $stateProvider.state('empresas.cadastrar', {
            url: '/cadastro',
            controller: 'EmpresaCadastroController',
            templateUrl: 'empresas/cadastrar/cadastrar.tpl.html'
        });
    }])

    .controller('EmpresaCadastroController', ['$scope', '$state', '$log', 'Empresa',
        function ($scope, $state, $log, Empresa) {

            $log.debug('cadastro');
            $scope.empresa = {
                telefones : [],
                enderecos : []
            };


            $scope.cadastrar = function () {
                $log.debug('Enviando cadastro para o endpoint', $scope.empresa);
                $scope.result = Empresa.save($scope.empresa, function () {
                    $log.debug('Empresa cadastrada:', $scope.result);
                    $state.transitionTo('empresas.listar');
                });
            };

            $scope.salvarContinuar = function () {
                $log.debug('Enviando cadastro para o endpoint', $scope.empresa);
                $scope.result = Empresa.save($scope.empresa, function () {
                    $log.debug('Empresa cadastrada:', $scope.result);
                    $scope.empresa = {
                        telefones : [],
                        enderecos : []
                    };
                });
            };

            $scope.voltar = function () {
                $state.transitionTo('empresas.listar');
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
                $log.debug('enderecos : ', $scope.empresa.enderecos);
            };

            $scope.removerEndereco = function (endereco) {
                $log.debug('removendo o endereco', endereco);
                $scope.empresa.enderecos.splice($scope.empresa.enderecos.indexOf(endereco), 1);
            };

    }])

;