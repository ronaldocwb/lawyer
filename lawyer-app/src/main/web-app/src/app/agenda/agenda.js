angular.module('lawyer.agenda', [
        'lawyer.Agenda',
        'lawyer.Evento'
    ])

    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('agenda', {
            url: '/agenda',
            views: {
                "main": {
                    controller: 'AgendaController',
                    templateUrl: 'agenda/agenda.tpl.html'
                }
            }

        });
    }])

    .controller('AgendaController', ['$scope', '$window', function ($scope, $window) {

    }])
;