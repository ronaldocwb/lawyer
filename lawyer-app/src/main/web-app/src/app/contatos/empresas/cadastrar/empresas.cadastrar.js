angular.module('lawyer.empresas.cadastro', [
        'ui.bootstrap',
    ])

    .config(['$stateProvider',  function config($stateProvider) {
        $stateProvider.state('empresas.cadastrar', {
            url: '/cadastro',
            controller: 'EmpresaCadastroController',
            templateUrl: 'contatos/empresas/cadastrar/empresas.cadastrar.tpl.html'
        });
    }])

    .controller('EmpresaCadastroController', ['$scope', '$state', '$log', 'Empresa', 'Municipio', 'notifications', '$http',
        function ($scope, $state, $log, Empresa, Municipio, notifications, $http) {

            $scope.empresa = {
                telefones : [],
                enderecos : []
            };


            $scope.cadastrar = function () {
                $log.debug('Enviando cadastro para o endpoint', $scope.empresa);
                $scope.empresa = Empresa.save($scope.empresa, function (result) {
                    $log.debug('Empresa cadastrada:', $scope.empresa);
                    notifications.pushForCurrentRoute('empresa.salva', 'success', {nome : $scope.empresa.nomeFantasia});
                    if ($scope.empresas) {
                        $scope.empresas.content.push($scope.empresa);
                    }
                    $state.go('empresas.listar');
                });
            };

            $scope.salvarContinuar = function (cadastro) {
                $log.debug('Enviando cadastro para o endpoint', $scope.empresa);
                $scope.empresa = Empresa.save($scope.empresa, function () {
                    $log.debug('Empresa cadastrada:', $scope.empresa);
                    notifications.pushForCurrentRoute('empresa.salva', 'success', {nome : $scope.empresa.nomeFantasia});
                    if ($scope.empresas) {
                        $scope.empresas.content.push($scope.empresa);
                    }
                    $scope.empresa = {
                        telefones : [],
                        enderecos : []
                    };
                });
            };

            $scope.add = function (key) {
                $scope.empresa[key].push({});
            };

            $scope.remove = function (key, $index) {
                $scope.empresa[key].splice($index, 1);
            };

            $scope.getMunicipios = function (value) {
                return $http.get('/lawyer/api/municipios?q='+value)
                    .then(function(results){
                        return results.data;
                    });
            };

            $scope.getEmpresas = function (value) {
                Empresa.query({q : value}, function (data) {
                    return data;
                });
            };

    }])

;