angular.module('lawyer.assuntos.cadastro', [
        'ui.mask'
    ])

    .config(['$stateProvider',  function config($stateProvider) {
        $stateProvider.state('assuntos.cadastro', {
            url: '/cadastro',
            controller: 'AssuntoCadastroController',
            templateUrl: 'assuntos/cadastro/assunto.cadastro.tpl.html'
        });
    }])

    .controller('AssuntoCadastroController', ['$scope', '$state', '$log', 'notifications', 'Assunto',
        function ($scope, $state, $log, notifications, Assunto) {

            $scope.assunto = {};
            $scope.salvar = function () {
                $scope.result = Assunto.save($scope.assunto, function () {
                    notifications.pushForNextRoute('assunto.salva', 'success', {nome : $scope.assunto.nome});
                    $scope.assuntos.content.push($scope.result);
                    $state.go('assuntos.listar');
                }, function () {
                    notifications.pushForNextRoute('assunto.erro.salvar', 'error', {nome : $scope.assunto.nome});
                });

            };
            $scope.voltar = function () {
                $state.go('assuntos.listar');
            };
    }])

;