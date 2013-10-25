angular.module('lawyer.pessoas.edicao', [
    ])

    .config(['$stateProvider',  function config($stateProvider) {
        $stateProvider.state('pessoas.editar', {
            url: '/editar/',
            controller: 'PessoaEdicaoController',
            templateUrl: 'contatos/pessoas/editar/pessoas.editar.tpl.html'
        });
    }])

    .controller('PessoaEdicaoController', ['$scope', 'notifications', '$log', 'Pessoa', '$state', '$stateParams', '$http',
        function ($scope, notifications, $log, Pessoa, $state, $stateParams, $http) {

            $scope.pessoa = $state.data;

            if ($state.data && $state.data.modal) {
                $scope.modal = $state.data.modal;
            }

            if (!$state.data && !$state.pessoa) {
                if ($stateParams.uid) {
                    $scope.pessoa = Pessoa.get({uid : $stateParams.uid});
                } else {
                    $state.go('pessoas.listar');
                }
            }

            $scope.salvar = function () {
                $scope.pessoa = Pessoa.update($scope.pessoa, function () {
                    notifications.pushForNextRoute('pessoa.alterada', 'success', {nome : $scope.pessoa.nome});
                    angular.noop($scope.modal ? $scope.modal.close(true) : $state.go('pessoas.listar'));
                });
            };

            $scope.voltar = function () {
                angular.noop($scope.modal ? $scope.modal.close(true) : $state.go('pessoas.listar'));
            };

            $scope.add = function (key) {
                $scope.pessoa[key].push({});
            };

            $scope.remove = function (key, $index) {
                $scope.pessoa[key].splice($index, 1);
            };

            $scope.getMunicipios = function (value) {
                return $http.get('/lawyer/api/municipios?q='+value)
                    .then(function(result){
                        return result.data;
                    });
            };

            $scope.getEmpresas = function (value) {
                return $http.get('/lawyer/api/empresas?q='+value+'&page=0&limit:5')
                    .then(function(results){
                        return results.data.content;
                    });
            };

        }])

;