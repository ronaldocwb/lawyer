angular.module('lawyer.usuarios.listar', [])
    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('contatos.listar-usuarios', {
            url: '/usuarios/',
            controller: 'ListarUsuariosController',
            templateUrl: 'contatos/usuarios/usuarios-listar.tpl.html'
        });
    }])
    .controller('ListarUsuariosController', ['$scope', 'Contato', function ($scope, Contato) {

        if (angular.equals($scope.usuarios, {})) {
            $scope.fetchUsuarios();
        }

        $scope.pesquisa = {
            query: '',
            inUse: false,
            hasUsed: false
        };

        $scope.buscar = function () {
            if ($scope.pesquisa.hasUsed === false) {
                $scope.originalResultSet = angular.copy($scope.usuarios);
                $scope.pesquisa.hasUsed = true;
            }

            $scope.pesquisa.inUse = true;
            if ($scope.pesquisa.query === '') {
                $scope.pesquisa.inUse = false;
            }
            $scope.usuarios = Contato.getUsuarios({q: $scope.pesquisa.query});
        };

        $scope.limparBusca = function () {
            $scope.usuarios = $scope.originalResultSet;
            $scope.pesquisa.query = '';
            $scope.pesquisa.inUse = false;
        };

        $scope.usuarios.current = 1;
        $scope.pageChanged = function (page) {
            $scope.usuarios = Contato.getUsuarios({q: $scope.pesquisa.inUse ? $scope.pesquisa.query : '', page: page - 1}, function () {
                $scope.usuarios.current = page;
            });
        };


    }])
;

