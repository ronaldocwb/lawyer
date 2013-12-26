angular.module('lawyer.Evento', [])
    .factory('Evento', ['$resource', function ($resource) {

        return $resource('/lawyer/api/eventos/:uid/', {uid: '@uid'}, {
            update  : { method: 'PUT' }
        });
    }])
;

