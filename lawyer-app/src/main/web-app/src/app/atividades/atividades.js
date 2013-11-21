angular.module('lawyer.atividades', [
        'lawyer.Atividade',
        'lawyer.Assunto',
        'formatCurrency',
        'formataReais',
        'formataHora'
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('atividades', {
            url: '/atividades',
            abstract: true,
            views: {
                "main": {
                    controller: 'AtividadesController',
                    template: '<div ui-view></div>'
                }
            },
            resolve: {
                atividades: function (Atividade) {
                    return Atividade.get();
                }
            }
        });
    }])

    .controller('AtividadesController', ['$scope', 'atividades', '$http',
        function ($scope, atividades, $http) {
            $scope.atividades = atividades;

            $scope.getAssuntos = function (value) {
                return $http.get('/lawyer/api/assuntos?q=' + value +'&page=0&limit:8')
                    .then(function (result) {
                        return result.data.content;
                    });
            };

            $scope.getUsuarios = function (value) {
                return $http.get('/lawyer/api/usuarios?q=' + value + '&page=0&limit:8')
                    .then(function (result) {
                        return result.data.content;
                    });
            };

        }])

;

