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
            $scope.advogado.geraUsuario = 'true';

            $scope.advogado.pessoa = {
                nome : '',
                telefones : [],
                emails : [],
                enderecos : []
            };

            $scope.changeNovaPessoa = function (value) {
                $scope.novaPessoa = value;
                if ($scope.novaPessoa === 'true') {
                    var nome = $scope.advogado.pessoa;
                    $scope.advogado.pessoa = {
                        nome : nome instanceof Object ? '' : nome,
                        telefones : [],
                        emails : [],
                        enderecos : []
                    };
                    $scope.esconderInfoPessoa = false;
                } else {
                    $scope.advogado.pessoa.nome = '';
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
                }, function error() {
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

            $scope.addEmpresa= function (name) {
                $scope.advogado.pessoa.empresa = {
                    nomeFantasia : name,
                    telefones : [],
                    enderecos : []
                };
                Empresa.save($scope.advogado.pessoa.empresa, function (empresa) {
                    $scope.advogado.pessoa.empresa = empresa;
                    // emite a notificação para completar cadastro: i18n, nome, callback e objeto
                    notifications.pushCompletarCadastro('empresa.completar.cadastro', { nome: name} , 'pessoa.cadastrar.empresa.callback', empresa);
                });
            };

            $scope.$on('pessoa.cadastrar.empresa.callback', function ($event, empresa) {
                $scope.completarEmpresa(empresa);
            });

            $scope.onSelectEmpresa = function (empresa) {
                $scope.advogado.pessoa.empresa = empresa;
            };

            $scope.completarEmpresa = function (empresa) {
                //envia o objeto de retorno no OK do modal para a popup de edicao, copia ele pra nao fazer bind automatico e aparecer alterando na tela de fundo.
                $state.data = angular.copy(empresa);
                $state.data.modal = $modal.open({
                    templateUrl: 'contatos/empresas/cadastrar/empresas.cadastrar.tpl.html',
                    controller: 'EmpresaEdicaoController',
                    resolve: {
                        empresa: function () {
                            return empresa;
                        }
                    }
                });

                $state.data.modal.result.then(function (empresaAtualizada) {
                    if (!empresaAtualizada) {
                        return;
                    }
                    // se atualizou a pessoa, ela existe e vamos substituir no array de responsaveis para a alteração ficar visivel na tela, e mostramos uma notificação de OK.
                    $scope.advogado.pessoa.empresa = empresaAtualizada;
                });
            };

            $scope.blur = function () {
                if (!$scope.advogado.pessoa) {
                    $scope.novaPessoa = 'true';
                    $scope.changeNovaPessoa('true');
                }
            };
            $scope.keyup = function () {
                if ($scope.novaPessoa === false && (!$scope.advogado.pessoa || !$scope.advogado.pessoa.uid)) {
                    $scope.esconderInfoPessoa = true;
                }
            };

    }])

;