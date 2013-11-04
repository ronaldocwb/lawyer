angular.module('lawyer.Usuario', [
        'ngResource'
    ])

    .factory('Usuario', function ($resource) {
        return $resource('/lawyer/api/usuarios/:uid', {uid: '@uid'}, {
            update  : { method: 'PUT' },
            current  : { params : {uid : 'atual'}, method: 'GET' }
        });
    })
;