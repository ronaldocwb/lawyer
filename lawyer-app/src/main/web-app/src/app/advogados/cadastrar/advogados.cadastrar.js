angular.module('lawyer.advogados.cadastro', [
    ])

    .config(['$stateProvider',  function config($stateProvider) {
        $stateProvider.state('advogados.cadastrar', {
            url: '/cadastro',
            controller: 'AdvogadoCadastroController',
            templateUrl: 'advogados/cadastrar/advogados.cadastrar.tpl.html'
        });
    }])

    .controller('AdvogadoCadastroController', ['$scope', '$state', '$log', 'Advogado', 'Municipio', 'notifications', '$http', 'Empresa', '$modal',
        function ($scope, $state, $log, Advogado, Municipio, notifications, $http, Empresa, $modal) {

            $scope.pessoa = {
                telefones : [],
                emails : [],
                enderecos : []
            };

            $scope.pushAdvogadoListagem = function () {
                if ($scope.advogados) {
                    $scope.advogados.content.push($scope.pessoa);
                }
            };

            $scope.cadastrar = function () {
                $scope.pessoa = Advogado.save($scope.pessoa, function () {
                    notifications.pushForNextRoute('pessoa.salva', 'success', {nome : $scope.pessoa.nome});
                    $scope.pushAdvogadoListagem();
                    $state.go('advogados.listar');
                }, function () {
                    notifications.pushForCurrentRoute('advogado.salva.erro', 'error', {nome : $scope.advogado.nome});
                });
            };

            $scope.salvarContinuar = function () {
                $scope.advogado = Advogado.save($scope.advogado, function () {
                    notifications.pushForCurrentRoute('advogado.salva', 'success', {nome : $scope.advogado.nome});
                    $scope.pushAdvogadoListagem();
                    $scope.advogado = {
                        telefones : [],
                        enderecos : [],
                        emails : []
                    };
                }, function () {
                    notifications.pushForCurrentRoute('advogado.salva.erro', 'error', {nome : $scope.advogado.nome});
                });
            };

            $scope.add = function (key) {
                $scope.advogado[key].push({});
            };

            $scope.remove = function (key, $index) {
                $scope.advogado[key].splice($index, 1);
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
                $scope.advogado.empresa = {
                    nomeFantasia : $item,
                    telefones : [],
                    enderecos : []
                };
                $scope.advogado.empresa = Empresa.save($scope.advogado.empresa, function () {
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
                            return $scope.advogado.empresa;
                        }
                    }
                });

                $state.data.modal.result.then(function (editar) {
                    if (editar === true) {
                        $state.data = $scope.advogado.empresa;
                    }
                });
            };


    }])

;