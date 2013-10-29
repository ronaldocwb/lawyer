angular.module('lawyer.pessoas', [
        'lawyer.Pessoa',
        'lawyer.Municipio',
        'lawyer.Empresa'
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('pessoas', {
            url: '/pessoas',
            abstract: true,
            views: {
                "main": {
                    controller: 'PessoaController',
                    template: '<div ui-view></div>'
                }
            },
            resolve: {
                pessoas: function (Pessoa) {
                    return Pessoa.get();
                }
            }
        });
    }])

    .controller('PessoaController', ['$scope', 'pessoas', '$rootScope', function ($scope, pessoas, $rootScope) {
        $scope.pessoas = pessoas;

    }])

;
