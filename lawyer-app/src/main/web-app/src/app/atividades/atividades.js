angular.module('lawyer.atividades', [
        'lawyer.Atividade',
        'lawyer.Assunto',
        'assuntoAutocomplete',
        'usuarioAutocomplete',
        'clienteAutocomplete',
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

    .controller('AtividadesController', ['$scope', 'atividades', 'assuntoAutocomplete', 'usuarioAutocomplete', 'clienteAutocomplete',
        function ($scope, atividades, assuntoAutocomplete, usuarioAutocomplete, clienteAutocomplete) {
            $scope.atividades = atividades;

            $scope.getAssuntos = function (value, cliente) {
                return assuntoAutocomplete.queryByClienteUid(value, cliente.uid);
            };

            $scope.getUsuarios = function (value) {
                return usuarioAutocomplete.query(value);
            };

            $scope.getClientes = function (value) {
                return clienteAutocomplete.query(value);
            };

        }])
;

