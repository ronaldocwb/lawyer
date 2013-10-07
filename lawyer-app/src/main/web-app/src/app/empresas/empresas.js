angular.module('lawyer.empresas', [
        'lawyer.Empresa',
        'lawyer.Municipio'
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
                empresas : function (Empresa) {
                    // get() é o metodo padrao para GET com singlke result.
                    // para listas utilizar o query(), que manda um GET e espera um array de retorno
                    return Empresa.get();
                }
            }
        });
    }])

    .controller('EmpresaController', ['$scope', '$location', '$state', '$log', 'empresas',
        function ($scope, $location, $state, $log, empresas) {

            $scope.empresas = empresas;

            if ($location.path().indexOf('cadastro') !== -1) {
                // acessando o cadastro direto pela url
                $state.transitionTo('empresas.cadastrar');
            } else {
                $state.transitionTo('empresas.listar');
            }

            $scope.$on('$stateChangeSuccess', function () {
                if ($state.current.url === '/empresas') {
                    $state.transitionTo('empresas.listar');
                }
            });
        }])

;
