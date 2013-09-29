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
            }
            // Recupera a lista de empresas. Após o término do promise, constrói a view e o controller.
            // empresas deverá ser injetado como parâmetro no construtor do controller.
        });
    }])

    .controller('EmpresaController', ['$scope', 'i18nNotifications', '$state', '$log', 'EmpresaResource',
        function ($scope, i18nNotifications, $state, $log, EmpresaResource) {
            if (!$scope.empresas) {
                $scope.empresas = EmpresaResource.query();
            }
            $state.transitionTo('empresas.listar');

            $scope.$on('$stateChangeSuccess', function () {
                if ($state.current.url === '/empresas') {
                    $state.transitionTo('empresas.listar');
                }
            });
        }])

;
