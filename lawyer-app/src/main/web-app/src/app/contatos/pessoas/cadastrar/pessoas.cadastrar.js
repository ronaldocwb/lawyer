angular.module('lawyer.pessoas.cadastro', [
        'ui.bootstrap',
    ])

    .config(['$stateProvider',  function config($stateProvider) {
        $stateProvider.state('pessoas.cadastrar', {
            url: '/cadastro',
            controller: 'PessoaCadastroController',
            templateUrl: 'contatos/pessoas/cadastrar/pessoas.cadastrar.tpl.html'
        });
    }])

    .controller('PessoaCadastroController', ['$scope', '$state', '$log', 'Pessoa', 'Municipio', 'notifications',
        function ($scope, $state, $log, Pessoa, Municipio, notifications) {

            $scope.pessoa = {
                telefones : [],
                enderecos : []
            };


            $scope.cadastrar = function () {
                $log.debug('Enviando cadastro para o endpoint', $scope.pessoa);
                $scope.pessoa = Pessoa.save($scope.pessoa, function () {
                    $log.debug('Pessoa cadastrada:', $scope.pessoa);
                    notifications.pushForNextRoute('pessoa.salva', 'success', {nome : $scope.pessoa.nomeFantasia});
                    $scope.pessoas.content.push($scope.pessoa);
                    $state.go('pessoas.listar');
                }, function () {
                    notifications.pushForNextRoute('pessoa.salva.erro', 'error', {nome : $scope.pessoa.nomeFantasia});
                });
            };

            $scope.salvarContinuar = function (cadastro) {
                $log.debug('Enviando cadastro para o endpoint', $scope.pessoa);
                $scope.pessoa = Pessoa.save($scope.pessoa, function () {
                    $log.debug('Pessoa cadastrada:', $scope.pessoa);
                    notifications.pushForCurrentRoute('pessoa.salva', 'success', {nome : $scope.pessoa.nomeFantasia});
                    $scope.pessoas.content.push($scope.pessoa);
                    $scope.pessoa = {
                        telefones : [],
                        enderecos : []
                    };
                }, function () {
                    notifications.pushForCurrentRoute('pessoa.salva.erro', 'error', {nome : $scope.pessoa.nomeFantasia});
                });
            };

            $scope.addTelefone = function () {
                $log.debug('Adicionando novo campo de telefone');
                $scope.pessoa.telefones.push({});
                $log.debug('telefones: ', $scope.pessoa.telefones);
            };

            $scope.removerTelefone = function (telefone) {
                $log.debug('removendo o telefone', telefone);
                $scope.pessoa.telefones.splice($scope.pessoa.telefones.indexOf(telefone), 1);
            };

            $scope.addEndereco = function () {
                $log.debug('Adicionando novo campo de endereco');
                $scope.pessoa.enderecos.push({});
                $log.debug('enderecos : ', $scope.pessoa.enderecos);
            };

            $scope.removerEndereco = function (endereco) {
                $log.debug('removendo o endereco', endereco);
                $scope.pessoa.enderecos.splice($scope.pessoa.enderecos.indexOf(endereco), 1);
            };

            $scope.getMunicipios = function (value) {
                Municipio.query({q : value}, function (data) {
                    return data;
                });

            };

    }])

;