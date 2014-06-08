
angular.module('lawyer.assunto.edicao', [
    ])

    .config(['$stateProvider',  function config($stateProvider) {
        $stateProvider.state('assunto.edicao', {
            url: '/edicao',
            controller: 'AssuntoEdicaoController',
            templateUrl: 'assunto/cadastro/assunto.cadastro.tpl.html'
        });
    }])

    .controller('AssuntoEdicaoController', ['$scope', 'notifications', 'Assunto', '$state',
        function ($scope, notifications, Assunto, $state) {

            if (!$state.data) {
                $state.transitionTo('assunto');
            }

            $scope.assunto = $state.data;




        }])

;