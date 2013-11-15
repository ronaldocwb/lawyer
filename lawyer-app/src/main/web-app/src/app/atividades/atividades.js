angular.module('lawyer.atividades', [
        'lawyer.Atividade',
        'lawyer.Assunto'
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('atividades', {
            url: '/atividades',
            abstract: true,
            views: {
                "main": {
                    controller: 'AtividadesController',
                    template: '<div ui-view></div>'
                }
            },
            resolve: {
                atividades: function (Atividade) {
                    console.log('LOL');
                    return Atividade.get();
                }
            }
        });
    }])

    .controller('AtividadesController', ['$scope', 'atividades',
        function ($scope, atividades) {
            $scope.atividades = atividades;

        }])

;

