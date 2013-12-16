angular.module('lawyer.usuarios', [
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('usuarios', {
            url: '/usuarios',
            views: {
                "main": {
                    controller: 'UsuariosController',
                    templateUrl: 'configuracoes/usuarios/usuarios.tpl.html'
                }
            }
//            auth : 'ADMINISTRADOR'
        });
    }])

    .controller('UsuariosController', ['$scope', '$log', 'Usuario', function ($scope, $log, Usuario) {
        $scope.usuarios = Usuario.get();
    }])
;
