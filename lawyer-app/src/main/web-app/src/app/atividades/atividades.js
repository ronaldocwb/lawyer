angular.module('lawyer.atividades', [
        'lawyer.Atividade',
        'lawyer.Assunto',
        'assuntoAutocomplete',
        'usuarioAutocomplete',
        'contatoAutocomplete',
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

    .controller('AtividadesController', ['$scope', 'atividades', 'assuntoAutocomplete', 'usuarioAutocomplete', 'contatoAutocomplete',
        function ($scope, atividades, assuntoAutocomplete, usuarioAutocomplete, contatoAutocomplete) {
            $scope.atividades = atividades;

            $scope.getAssuntos = function (value, contato) {
                return assuntoAutocomplete.queryByContatoUid(value, contato.uid);
            };

            $scope.getUsuarios = function (value) {
                return usuarioAutocomplete.query(value);
            };

            $scope.getClientes = function (value) {
                return contatoAutocomplete.queryClientes(value);
            };

        }])
;

