angular.module('lawyer.assuntos', [
        'lawyer.Assunto'
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('assuntos', {
            url: '/assuntos',
            views: {
                "main": {
                    controller: 'AssuntosController',
                    template: '<div ui-view></div>'
                }
            },
            resolve: {
                assuntos: function (Assunto) {
                    return Assunto.get();
                }
            }
        });
    }])

    .controller('AssuntosController', ['$scope', 'notifications', '$state', '$log', 'Assunto', 'assuntos',
        function ($scope, notifications, $state, $log, assunto, assuntos) {
            $scope.assuntos = assuntos;

        }])
;

