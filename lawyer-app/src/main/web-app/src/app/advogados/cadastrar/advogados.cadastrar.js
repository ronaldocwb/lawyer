angular.module('lawyer.advogados.cadastro', [
    ])

    .config(['$stateProvider',  function config($stateProvider) {
        $stateProvider.state('advogados.cadastrar', {
            url: '/cadastro',
            controller: 'AdvogadoCadastroController',
            templateUrl: 'advogados/cadastrar/advogados.cadastrar.tpl.html'
        });
    }])

    .controller('AdvogadoCadastroController', ['$scope', '$state', '$log', 'Advogado', 'Municipio', 'notifications', 'Empresa', '$modal',
        function ($scope, $state, $log, Advogado, Municipio, notifications, Empresa, $modal) {

            $scope.advogado = {};
            $scope.novaPessoa = 'true';

            $scope.advogado.pessoa = {
                nome : '',
                telefones : [],
                emails : [],
                enderecos : []
            };

            $scope.changeNovaPessoa = function (value) {
                $scope.novaPessoa = value;
                if ($scope.novaPessoa === true) {
                    $scope.advogado.pessoa = {
                        nome : '',
                        telefones : [],
                        emails : [],
                        enderecos : []
                    };
                    $scope.esconderInfoPessoa = false;
                } else {
                    $scope.esconderInfoPessoa = true;
                }

            };

            $scope.onSelectPessoa = function () {
                Advogado.buscarPorPessoaUid({}, $scope.advogado, function (result) {
                    if (result.uid != null) {
                        notifications.pushForCurrentRoute('advogado.ja.existe', 'warning', { nome : result.pessoa.nome});
                        $scope.esconderInfoPessoa = true;
                        $scope.advogado.pessoa = {
                            nome : '',
                            telefones : [],
                            emails : [],
                            enderecos : []
                        };
                    } else {
                        $scope.esconderInfoPessoa = false;
                    }
                }, function () {
                    $scope.esconderInfoPessoa = false;
                });
            };

            $scope.pushAdvogadoListagem = function () {
                if ($scope.advogados) {
                    $scope.advogados.content.push($scope.advogado);
                }
            };

            $scope.cadastrar = function () {
                $scope.advogado = Advogado.save($scope.advogado, function () {
                    notifications.pushForNextRoute('advogado.salva', 'success', {nome : $scope.advogado.pessoa.nome});
                    $scope.pushAdvogadoListagem();
                    $state.go('advogados.listar');
                }, function () {
                    notifications.pushForCurrentRoute('advogado.salva.erro', 'error', {nome : $scope.advogado.pessoa.nome});
                });
            };

            $scope.salvarContinuar = function () {
                $scope.advogado = Advogado.save($scope.advogado, function () {
                    notifications.pushForCurrentRoute('advogado.salva', 'success', {nome : $scope.advogado.nome});
                    $scope.pushAdvogadoListagem();

                    $scope.advogado = {};
                    $scope.advogado.pessoa = {
                        telefones : [],
                        enderecos : [],
                        emails : []
                    };
                }, function () {
                    notifications.pushForCurrentRoute('advogado.salva.erro', 'error', {nome : $scope.advogado.nome});
                });
            };

            $scope.add = function (key) {
                $scope.advogado.pessoa[key].push({});
            };

            $scope.remove = function (key, $index) {
                $scope.advogado.pessoa[key].splice($index, 1);
            };

            $scope.notification = {};
            $scope.addEmpresa = function ($item) {
                $scope.advogado.pessoa.empresa = {
                    nomeFantasia : $item,
                    telefones : [],
                    enderecos : []
                };
                $scope.advogado.pessoa.empresa = Empresa.save($scope.advogado.empresa, function () {
                    $scope.notification = {
                        text : 'A empresa <b>' + $item + '</b> foi criada!'
                    };
                });
            };

            $scope.completarEmpresa = function () {
                $state.data = $scope.advogado.empresa;
                $state.data.modal = $modal.open({
                    templateUrl: 'empresas/editar/empresas.editar.tpl.html',
                    controller: 'EmpresaEdicaoController',
                    resolve: {
                        empresa: function () {
                            return $scope.advogado.pessoa.empresa;
                        }
                    }
                });

                $state.data.modal.result.then(function (editar) {
                    if (editar === true) {
                        $state.data = $scope.advogado.pessoa.empresa;
                    }
                });
            };

            $scope.advogadoViaPessoa = false;
            $scope.trocarParaPessoaCadastrada = function () {
                $scope.advogadoViaPessoa = !$scope.advogadoViaPessoa;
            };

    }])

;