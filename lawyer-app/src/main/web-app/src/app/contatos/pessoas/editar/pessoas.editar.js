
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
                $scope.pessoa = Pessoa.update($scope.pessoa, function () {
                    $log.debug('Pessoa alterada:', $scope.pessoa);
                    notifications.pushForCurrentRoute('pessoa.alterada', 'success', {nome : $scope.pessoa.nome});
                    $state.go('pessoas.listar');
                });
            };

            $scope.voltar = function () {
                $state.go('pessoas.listar');
            };

            $scope.add = function (key) {
                $scope.pessoa[key].push({});
            };

            $scope.remove = function (key, $index) {
                $scope.pessoa[key].splice($index, 1);
            };

        }])

;