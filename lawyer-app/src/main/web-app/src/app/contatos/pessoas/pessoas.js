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
            }
        });
    }])

    .controller('PessoaController', ['$scope', '$location', '$state', '$log',
        function ($scope, $location, $state, $log) {

        }])

;
