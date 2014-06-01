angular.module('lawyer.configuracoes.conta', [
        'lawyer.Usuario',
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('conta', {
            url: '/conta',
            views: {
                "main": {
                    controller: 'ContaController',
                    templateUrl: 'configuracoes/conta/conta.tpl.html'
                }
            },
            resolve: {
                usuario: function (Usuario) {
                    return Usuario.current();
                }
            }
        });
    }])

    .controller('ContaController', ['$scope', 'Usuario', 'usuario', 'notifications', '$log',
        function ($scope, Usuario, usuario, notifications, $log) {
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
                    notifications.pushForCurrentRoute('config.senha.diferentes', 'error');
                    $("#novaSenha").focus();
                }
                if ($scope.mensagens.length === 0) {
                    $scope.usuario.senha = $scope.user.senha;
                    $scope.usuario.novaSenha = $scope.user.novaSenha;
                    Usuario.updateSenha($scope.usuario, function () {
                        $log.debug('usuario atualizado!');
                        notifications.pushForCurrentRoute('config.senha.alterada.sucesso', 'success');
                    }, function error(restException){
                        notifications.pushForCurrentRoute('config.senha.alteracao.falha', 'error', {exception: restException.data.message});
                    });
                }
            };
        }])
;

