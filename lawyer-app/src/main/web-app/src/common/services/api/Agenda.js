angular.module('lawyer.Agenda', [])
    .factory('Agenda', ['$resource', function ($resource) {

        return $resource('/lawyer/api/agendas/:uid/:eventos', {uid: '@uid'}, {
            update  : { method: 'PUT' },
            getEventos : { params : { eventos : 'eventos' }, method : 'GET'}
        });
    }])
;

