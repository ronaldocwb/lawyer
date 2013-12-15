angular.module('lawyer.configuracoes.conta', [
        'lawyer.Usuario',
        'ui.validate'
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('conta', {
            url: '/conta',
            views: {
                "main": {
                    controller: 'ContaController',
                    templateUrl: 'conta/conta.tpl.html'
                }
            },
            resolve: {
                usuario: function (Usuario) {
                    return Usuario.current();
                }
            }
        });
    }])

    .controller('ContaController', ['$scope', 'Usuario', 'usuario', '$log',
        function ($scope, Usuario, usuario, $log) {
            console.log(usuario);
            $scope.usuario = usuario;
            $scope.user = {
                novaSenha : null,
                senha : null,
                confirmaNovaSenha : null
            };

            $scope.alterar = function () {
                $scope.mensagens = [];
                if ($scope.user.novaSenha !== $scope.user.confirmaNovaSenha) {
                    $scope.mensagens.push("Senhas não conferem.");
                    $("#novaSenha").focus();
                }
                if ($scope.user.novaSenha === $scope.user.senha) {
                    $scope.mensagens.push("Senhas devem ser diferentes.");
                    $("#senha").focus();
                }
                if ($scope.mensagens.length === 0) {
                    $scope.usuario.senha = $scope.user.senha;
                    $scope.usuario.novaSenha = $scope.user.novaSenha;
                    Usuario.updateSenha($scope.usuario, function () {
                        $log.debug('usuario atualizado!');
                    });
                }
            };
        }])
;

