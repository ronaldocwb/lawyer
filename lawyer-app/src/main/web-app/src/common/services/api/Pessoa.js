angular.module('lawyer.Pessoa', [
        'ngResource'
    ])

    .factory('Pessoa', function ($resource) {
        return $resource('/lawyer/api/pessoas/:uid', {uid: '@uid'}, {
            update  : { method: 'PUT' }
        });
    })
;