angular.module('lawyer.pessoas', [
        'lawyer.Pessoa',
        'lawyer.Municipio'
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('pessoas', {
            url: '/pessoas',
            abstract : true,
            views: {
                "main": {
                    controller: 'PessoaController',
                    template: '<div ui-view></div>'
                }
            }, resolve : {
                // Recupera a lista de pessoas. Após o término do promise, constrói a view e o controller.
                // pessoas deverá ser injetado como parâmetro no construtor do controller.
                pessoas : function (Pessoa) {
                    // get() é o metodo padrao para GET com singlke result.
                    // para listas utilizar o query(), que manda um GET e espera um array de retorno
                    return Pessoa.get();
                }
            }
        });
    }])

    .controller('PessoaController', ['$scope', '$location', '$state', '$log', 'pessoas', 'notifications',
        function ($scope, $location, $state, $log, pessoas, notifications) {

            $scope.pessoas = pessoas;

            if ($location.path().indexOf('cadastro') !== -1) {
                // acessando o cadastro direto pela url
                $state.go('pessoas.cadastrar');
            } else {
                $state.go('pessoas.listar');
            }

            $scope.$on('$stateChangeSuccess', function () {
                if ($state.current.url === '/pessoas' || $state.current.url === '/pessoas/') {
                    $state.go('pessoas.listar');
                }
            });
        }])

;
