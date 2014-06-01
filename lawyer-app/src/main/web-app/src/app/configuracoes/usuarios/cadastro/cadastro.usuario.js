angular.module('lawyer.usuarios.cadastro', [
        'municipioAutocomplete'
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('cadastrar-usuario', {
            url: '/usuarios/cadastrar/:tipo',
            views: {
                "main": {
                    controller: 'CadastrarUsuarioController',
                    templateUrl: 'configuracoes/usuarios/cadastro/cadastro.usuario.tpl.html'
                }
            }

        });
    }])

    .controller('CadastrarUsuarioController', ['$scope', '$state', '$log', 'Usuario', 'Municipio', 'notifications', '$stateParams', 'municipioAutocomplete',
        function ($scope, $state, $log, Usuario, Municipio, notifications, $stateParams, municipioAutocomplete) {

            $scope.usuario = {
                criarContato: true,
                permissoes: [],
                tipoUsuario: $stateParams.tipo || 'usuario',
                pessoa: {
                    nome: '',
                    telefones: [],
                    emails: [],
                    enderecos: []
                },
                advogado: {}

            };

            $scope.permissoes = [ { nome: 'Administrador', enum: 'ADMIN', selected : false },
                { nome: 'Financeiro', enum: 'FINANCEIRO', selected : false },
                { nome: 'Relatórios', enum: 'RELATORIOS', selected : false },
                { nome: 'Usuários', enum: 'USUARIOS', selected : false }
            ];

            $scope.toggleSelection = function toggleSelection(permissao) {
                var idx = $scope.usuario.permissoes.indexOf(permissao.enum);
                angular.noop(idx > -1 ? $scope.usuario.permissoes.splice(idx, 1) : $scope.usuario.permissoes.push(permissao.enum));
            };

            $scope.pushAdvogadoListagem = function () {
                if ($scope.usuarios) {
                    $scope.usuarios.content.push($scope.usuario);
                }
            };

            $scope.cadastrar = function () {
                $scope.usuario.tipoUsuario = $scope.usuario.tipoUsuario.toUpperCase();
                $scope.usuario = Usuario.save($scope.usuario, function (usuario) {
                    notifications.pushForNextRoute('advogado.salva', 'success', {nome: usuario.pessoa.nome});
                    $scope.pushAdvogadoListagem();
                    $state.go('usuarios');
                }, function () {
                    notifications.pushForCurrentRoute('advogado.salva.erro', 'error', {nome: $scope.usuario.pessoa.nome});
                });
            };

            $scope.salvarContinuar = function () {
                $scope.usuario = Usuario.save($scope.usuario, function () {
                    notifications.pushForCurrentRoute('advogado.salva', 'success', {nome: $scope.usuario.nome});
                    $scope.pushAdvogadoListagem();

                    $scope.usuario = {
                        criarContato: true,
                        permissoes: [],
                        pessoa: {
                            nome: '',
                            telefones: [],
                            emails: [],
                            enderecos: []
                        },
                        advogado: {
                            numeroOAB: ''
                        }
                    };
                }, function () {
                    notifications.pushForCurrentRoute('advogado.salva.erro', 'error', {nome: $scope.usuario.nome});
                });
            };

            $scope.add = function (key) {
                $scope.usuario.pessoa[key].push({});
            };

            $scope.remove = function (key, $index) {
                $scope.usuario.pessoa[key].splice($index, 1);
            };

            $scope.getMunicipios = function (value) {
                return municipioAutocomplete.query(value);
            };


        }])

;