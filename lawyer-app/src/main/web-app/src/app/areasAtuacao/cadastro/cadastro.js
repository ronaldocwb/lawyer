angular.module('lawyer.areasAtuacao.cadastro', [
        'ui.mask'
    ])

    .config(['$stateProvider',  function config($stateProvider) {
        $stateProvider.state('areasAtuacao.cadastro', {
            url: '/cadastro',
            controller: 'AreaAtuacaoCadastroController',
            templateUrl: 'areasAtuacao/cadastro/cadastro.tpl.html'
        });
    }])

    .controller('AreaAtuacaoCadastroController', ['$scope', 'notifications', '$log', 'AreaAtuacaoResource',
        function ($scope, notifications, $log, AreaAtuacaoResource) {

            $scope.cadastrar = function () {
                $log.debug('Enviando cadastro para o endpoint', $scope.areaAtuacao);
                $scope.result = AreaAtuacaoResource.save({}, $scope.areaAtuacao, function () {
                    $log.debug('AreaAtuacao cadastrada:', $scope.result);
                    $log.debug('Mostrar botao para voltar');
                });

            };

    }])

;