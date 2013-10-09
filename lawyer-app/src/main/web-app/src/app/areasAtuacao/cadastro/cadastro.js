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

    .controller('AreaAtuacaoCadastroController', ['$scope', '$state', '$log', 'i18nNotifications', 'AreaAtuacaoResource',
        function ($scope, $state, $log, i18nNotifications, AreaAtuacaoResource) {

            $scope.cadastrar = function () {
                $log.debug('Enviando cadastro para o endpoint', $scope.areaAtuacao);
                $scope.result = AreaAtuacaoResource.save({}, $scope.areaAtuacao, function () {
                    $log.debug('AreaAtuacao cadastrada:', $scope.result);
                    $log.debug('Mostrar botao para voltar');
                    $scope.areasAtuacao.content._push($scope.result)
                    $state.transitionTo('areasAtuacao.listar');
                });

            };

    }])

;