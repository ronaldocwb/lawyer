angular.module('lawyer.areasAtuacao.cadastro', [
        'ui.mask'
    ])

    .config(['$stateProvider',  function config($stateProvider) {
        $stateProvider.state('areasAtuacao.cadastro', {
            url: '/cadastro',
            controller: 'AreaAtuacaoCadastroController',
            templateUrl: 'areasAtuacao/cadastro/atuacao.cadastro.tpl.html'
        });
    }])

    .controller('AreaAtuacaoCadastroController', ['$scope', '$state', '$log', 'notifications', 'AreaAtuacao',
        function ($scope, $state, $log, notifications, AreaAtuacao) {

            $scope.areaAtuacao = {};
            $scope.salvar = function () {
                console.log($scope.areaAtuacao);
                $scope.result = AreaAtuacao.save($scope.areaAtuacao, function () {
                    notifications.pushForNextRoute('areaAtuacao.salva', 'success');
                    $scope.areasAtuacao.content.push($scope.result);
                    $state.go('areasAtuacao.listar');
                });

            };
            $scope.voltar = function () {
                $state.go('areasAtuacao.listar');
            };
    }])

;