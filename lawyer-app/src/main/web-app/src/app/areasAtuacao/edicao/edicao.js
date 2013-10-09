
angular.module('lawyer.areasAtuacao.edicao', [
        'ui.mask'
    ])

    .config(['$stateProvider',  function config($stateProvider) {
        $stateProvider.state('areasAtuacao.edicao', {
            url: '/edicao',
            controller: 'AreaAtuacaoEdicaoController',
            templateUrl: 'areasAtuacao/edicao/edicao.tpl.html'
        });
    }])

    .controller('AreaAtuacaoEdicaoController', ['$scope', 'notifications', '$log', 'AreaAtuacaoResource', '$state', '$stateParams',
        function ($scope, notifications, $log, AreaAtuacaoResource, $state, $stateParams) {

            // $state nao possui a areaAtuacao para alterar. Volta pra pagina anterior.
            if (!$state.data) {
                $state.transitionTo('areasAtuacao');
            }

            $scope.areaAtuacao = $state.data;

            $scope.salvar = function () {
                $log.debug('Enviando cadastro para o endpoint', $scope.areaAtuacao);
                $scope.result = AreaAtuacaoResource.update({id : $scope.areaAtuacao.uid}, $scope.areaAtuacao, function () {
                    $log.debug('AreaAtuacao alterada:', $scope.result);
                    $log.debug('Mostrar botao para voltar');
                    notifications.pushForNextRoute('areaAtuacao.salva', 'success');
                    $state.transitionTo('areasAtuacao.listar');
                });

            };

            $scope.addTelefone = function () {
                $log.debug('Adicionando novo campo de telefone');
                $scope.areaAtuacao.telefones.push({});
                $log.debug('telefones: ', $scope.areaAtuacao.telefones);
            };

            $scope.removerTelefone = function (telefone) {
                $log.debug('removendo o telefone', telefone);
                $scope.areaAtuacao.telefones.splice($scope.areaAtuacao.telefones.indexOf(telefone), 1);
            };

            $scope.addEndereco = function () {
                $log.debug('Adicionando novo campo de endereco');
                $scope.areaAtuacao.enderecos.push({});
                $log.debug('telefones: ', $scope.areaAtuacao.enderecos);
            };

            $scope.removerEndereco = function (endereco) {
                $log.debug('removendo o endereco', endereco);
                $scope.areaAtuacao.enderecos.splice($scope.areaAtuacao.enderecos.indexOf(endereco), 1);
            };

        }])

;