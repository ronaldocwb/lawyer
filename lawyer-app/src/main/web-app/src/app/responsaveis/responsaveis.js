angular.module('lawyer.responsaveis', [
        'lawyer.Responsavel',
        'lawyer.Municipio'
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('responsaveis', {
            url: '/responsaveis',
            abstract : true,
            views: {
                "main": {
                    controller: 'ResponsavelController',
                    template: '<div ui-view></div>'
                }
            }, resolve : {
            }
        });
    }])

    .controller('ResponsavelController', ['$scope', '$location', '$state', '$log',
        function ($scope, $location, $state, $log) {

            $scope.pessoas = pessoas;

        }])

;
