angular.module('lawyer.empresas', [
        'lawyer.Empresa',
        'lawyer.Municipio'
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('empresas', {
            url: '/empresas',
            abstract : true,
            views: {
                "main": {
                    controller: 'EmpresaController',
                    template: '<div ui-view></div>'
                }
            }, resolve : {
                // Recupera a lista de empresas. Após o término do promise, constrói a view e o controller.
                // empresas deverá ser injetado como parâmetro no construtor do controller.
                empresas : function (Empresa) {
                    // get() é o metodo padrao para GET com singlke result.
                    // para listas utilizar o query(), que manda um GET e espera um array de retorno
                    return Empresa.get();
                }
            }
        });
    }])

    .controller('EmpresaController', ['$scope', '$location', '$state', '$log', 'empresas', 'notifications',
        function ($scope, $location, $state, $log, empresas, notifications) {

            $scope.empresas = empresas;

            if ($location.path().indexOf('cadastro') !== -1) {
                // acessando o cadastro direto pela url
                $state.go('empresas.cadastrar');
            } else {
                $state.go('empresas.listar');
            }

            $scope.$on('$stateChangeSuccess', function () {
                if ($state.current.url === '/empresas' || $state.current.url === '/empresas/') {
                    $state.go('empresas.listar');
                }
            });
        }])

;
