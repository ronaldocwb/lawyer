angular.module('lawyer.configuracoes.conta', [
        'lawyer.Usuario'
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

    .controller('ContaController', ['$scope', 'Usuario', 'usuario',
        function ($scope, Usuario, usuario) {
            $scope.usuario = usuario;
            $scope.user = {
                novaSenha : null,
                senha : null,
                confirmaNovaSenha : null
            };

            $scope.alterar = function (alterarSenhaForm) {
                $scope.mensagens = [];
                if ($scope.user.senha !== $scope.usuario.senha) {
                    $scope.mensagens.push("Senha incorreta.");
                }
                if ($scope.user.novaSenha !== $scope.user.confirmaNovaSenha) {
                    $scope.mensagens.push("Senhas não conferem.");
                }
                if ($scope.user.novaSenha !== $scope.user.senha) {
                    $scope.mensagens.push("Senhas iguais.");
                }
            };
        }])
;

