angular.module('lawyer.Assunto', [
        'ngResource'
    ])

    .factory('Assunto', function ($resource) {
        return $resource('/lawyer/api/assuntos/:uid', {uid: '@uid'}, {
            update  : { method: 'PUT' }
        });
    })
;
