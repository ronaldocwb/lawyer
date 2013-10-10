
angular.module('lawyer.pessoas.edicao', [
        'ui.mask'
    ])

    .config(['$stateProvider',  function config($stateProvider) {
        $stateProvider.state('pessoas.editar', {
            url: '/editar/',
            controller: 'PessoaEdicaoController',
            templateUrl: 'contatos/pessoas/editar/pessoas.editar.tpl.html'
        });
    }])

    .controller('PessoaEdicaoController', ['$scope', 'notifications', '$log', 'Pessoa', '$state', '$stateParams',
        function ($scope, notifications, $log, Pessoa, $state, $stateParams) {

            $scope.pessoa = $state.data;

            // $state não possui a empresa para alterar. Volta pra pagina anterior.
            if (!$state.data && !$state.pessoa) {
                if ($stateParams.uid) {
                    $scope.pessoa = Pessoa.get({uid : $stateParams.uid});
                } else {
                    $state.go('pessoas.listar');
                }
            }

            $scope.salvar = function () {
                $log.debug('Enviando cadastro para o endpoint', $scope.pessoa);
                $scope.pessoa = new Pessoa($scope.pessoa);
                $scope.pessoa.$update(function () {
                    $log.debug('Pessoa alterada:', $scope.pessoa);
                    notifications.pushForCurrentRoute('empresa.alterada', 'success', {nome : $scope.pessoa.nomeFantasia});
                    $state.go('pessoas.listar');
                });

                // teste --> mesma coisa do que está acima, mas sem instanciar diretamente a Pessoa e recuperar um $resource
//                $scope.pessoa = PessoaResource.update({uid : $scope.pessoa.uid}, $scope.pessoa, function () {
//                    $log.debug('Pessoa alterada:', $scope.pessoa);
//                    $log.debug('Mostrar botao para voltar');
//                });

            };


            $scope.voltar = function () {
                $state.go('pessoas.listar');
            };

            $scope.addTelefone = function () {
                $log.debug('Adicionando novo campo de telefone');
                $scope.pessoa.telefones.push({});
                $log.debug('telefones: ', $scope.pessoa.telefones);
            };

            $scope.removerTelefone = function (telefone) {
                $log.debug('removendo o telefone', telefone);
                $scope.pessoa.telefones.splice($scope.pessoa.telefones.indexOf(telefone), 1);
            };

            $scope.addEndereco = function () {
                $log.debug('Adicionando novo campo de endereco');
                $scope.pessoa.enderecos.push({});
                $log.debug('telefones: ', $scope.pessoa.enderecos);
            };

            $scope.removerEndereco = function (endereco) {
                $log.debug('removendo o endereco', endereco);
                $scope.pessoa.enderecos.splice($scope.pessoa.enderecos.indexOf(endereco), 1);
            };

        }])

;