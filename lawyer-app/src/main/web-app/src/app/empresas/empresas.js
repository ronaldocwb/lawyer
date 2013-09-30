angular.module('lawyer.empresas', [
        'EmpresaResource',
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('empresas', {
            url: '/empresas',
            views: {
                "main": {
                    controller: 'EmpresaController',
                    templateUrl: 'empresas/empresas.tpl.html'
                }
            }, resolve : {
                // Recupera a lista de empresas. Após o término do promise, constrói a view e o controller.
                // empresas deverá ser injetado como parâmetro no construtor do controller.
                empresas : function (EmpresaResource) {
                    return EmpresaResource.query();
                }
            }
        });
    }])

    .controller('EmpresaController', ['$scope', 'i18nNotifications', '$state', '$log', 'EmpresaResource', 'empresas',
        function ($scope, i18nNotifications, $state, $log, EmpresaResource, empresas) {

            $scope.empresas = empresas;

            $state.transitionTo('empresas.listar');

            $scope.$on('$stateChangeSuccess', function () {
                if ($state.current.url === '/empresas') {
                    $state.transitionTo('empresas.listar');
                }
            });
        }])

;
