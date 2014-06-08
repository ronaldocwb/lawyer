angular.module('lawyer.areasAtuacao', [
        'lawyer.AreaAtuacao'
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('areasAtuacao', {
            url: '/areasAtuacao',
            views: {
                "main": {
                    controller: 'AreaAtuacaoController',
                    template: '<div ui-view></div>'
                }
            }, resolve: {
                // Recupera a lista de areasAtuacao. Após o término do promise, constrói a view e o controller.
                // areasAtuacao deverá ser injetado como parâmetro no construtor do controller.
                areasAtuacao: function (AreaAtuacao) {
                    return AreaAtuacao.get();
                }
            }
        });
    }])

    .controller('AreaAtuacaoController', ['$scope', 'notifications', '$state', '$log', 'AreaAtuacao', 'areasAtuacao',
        function ($scope, notifications, $state, $log, AreaAtuacao, areasAtuacao) {

            $scope.areasAtuacao = areasAtuacao;

        }])

;
