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

    .controller('PessoaCadastroController', ['$scope', '$state', '$log', 'Pessoa', 'Municipio', 'notifications', '$http', 'Empresa', '$modal',
        function ($scope, $state, $log, Pessoa, Municipio, notifications, $http, Empresa, $modal) {

            $scope.pessoa = {
                telefones : [],
                emails : [],
                enderecos : []
            };

            $scope.cadastrar = function () {
                $log.debug('Enviando cadastro para o endpoint', $scope.pessoa);
                $scope.pessoa = Pessoa.save($scope.pessoa, function () {
                    $log.debug('Pessoa cadastrada:', $scope.pessoa);
                    notifications.pushForNextRoute('pessoa.salva', 'success', {nome : $scope.pessoa.nome});
                    if ($scope.pessoas) {
                        $scope.pessoas.content.push($scope.pessoa);
                    }
                    $state.go('pessoas.listar');
                }, function () {
                    notifications.pushForCurrentRoute('pessoa.salva.erro', 'error', {nome : $scope.pessoa.nome});
                });
            };

            $scope.salvarContinuar = function (cadastro) {
                $log.debug('Enviando cadastro para o endpoint', $scope.pessoa);
                $scope.pessoa = Pessoa.save($scope.pessoa, function () {
                    $log.debug('Pessoa cadastrada:', $scope.pessoa);
                    notifications.pushForCurrentRoute('pessoa.salva', 'success', {nome : $scope.pessoa.nome});
                    if ($scope.pessoas) {
                        $scope.pessoas.content.push($scope.pessoa);
                    }
                    $scope.pessoa = {
                        telefones : [],
                        enderecos : [],
                        emails : []
                    };
                }, function () {
                    notifications.pushForCurrentRoute('pessoa.salva.erro', 'error', {nome : $scope.pessoa.nome});
                });
            };

            $scope.addTelefone = function () {
                $log.debug('Adicionando novo campo de telefone');
                $scope.pessoa.telefones.push({});
                $log.debug('telefones: ', $scope.pessoa.telefones);
            };

            $scope.addEmail = function () {
                $log.debug('Adicionando novo campo de email');
                $scope.pessoa.emails.push({});
                $log.debug('telefones: ', $scope.pessoa.emails);
            };

            $scope.removerEmail = function (email) {
                $log.debug('removendo o email', email);
                $scope.pessoa.emails.splice($scope.pessoa.emails.indexOf(email), 1);
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
                return $http.get('/lawyer/api/municipios?q='+value)
                    .then(function(results){
                        return results.data;
                    });
            };
            $scope.getEmpresas = function (value) {
                return $http.get('/lawyer/api/empresas?q='+value+'&page=0&limit:5')
                    .then(function(results){
                        return results.data.content;
                    });
            };

            $scope.notification = {};
            $scope.addEmpresa = function ($item) {
                $scope.pessoa.empresa = {
                    nomeFantasia : $item,
                    telefones : [],
                    enderecos : []
                };
                $scope.pessoa.empresa = Empresa.save($scope.pessoa.empresa, function () {
                    $scope.notification = {
                        text : 'A empresa <b>' + $item + '</b> foi criada!'
                    };
                });
            };

            $scope.completarEmpresa = function () {
                $state.data = $scope.pessoa.empresa;
                $state.data.modal = $modal.open({
                    templateUrl: 'contatos/empresas/editar/empresas.editar.tpl.html',
                    controller: 'EmpresaEdicaoController',
                    resolve: {
                        empresa: function () {
                            return $scope.pessoa.empresa;
                        }
                    }
                });

                $state.data.modal.result.then(function (editar) {
                    if (editar === true) {
                        $state.data = $scope.pessoa.empresa;
                    }
                });
            };


    }])

;