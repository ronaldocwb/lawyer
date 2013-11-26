angular.module('lawyer.atividades', [
        'lawyer.Atividade',
        'lawyer.Assunto',
        'assuntoAutocomplete',
        'usuarioAutocomplete',
        'formataValorInput',
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

    .controller('AtividadesController', ['$scope', 'atividades', 'assuntoAutocomplete', 'usuarioAutocomplete',
        function ($scope, atividades, assuntoAutocomplete, usuarioAutocomplete) {
            $scope.atividades = atividades;

            $scope.getAssuntos = function (value) {
                return assuntoAutocomplete.query(value);
            };

            $scope.getUsuarios = function (value) {
                return usuarioAutocomplete.query(value);
            };

        }])
;

