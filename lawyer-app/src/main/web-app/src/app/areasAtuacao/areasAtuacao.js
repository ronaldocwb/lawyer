angular.module('lawyer.areasAtuacao', [
        'AreaAtuacaoResource',
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('areasAtuacao', {
            url: '/areasAtuacao',
            views: {
                "main": {
                    controller: 'AreaAtuacaoController',
                    templateUrl: 'areasAtuacao/areasAtuacao.tpl.html'
                }
            }, resolve : {
                // Recupera a lista de areasAtuacao. Após o término do promise, constrói a view e o controller.
                // areasAtuacao deverá ser injetado como parâmetro no construtor do controller.
                areasAtuacao : function (AreaAtuacaoResource) {
                    return AreaAtuacaoResource.query();
                }
            }
        });
    }])

    .controller('AreaAtuacaoController', ['$scope', 'notifications', '$state', '$log', 'AreaAtuacaoResource', 'areasAtuacao',
        function ($scope, notifications, $state, $log, AreaAtuacaoResource, areasAtuacao) {

            $scope.areasAtuacao = areasAtuacao;

            $state.transitionTo('areasAtuacao.listar');

            $scope.$on('$stateChangeSuccess', function () {
                if ($state.current.url === '/areasAtuacao') {
                    $state.transitionTo('areasAtuacao.listar');
                }
            });
        }])

;
