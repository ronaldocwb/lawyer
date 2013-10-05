angular.module('Empresa', [
        'ngResource'
    ])

    .factory('Empresa', function ($resource) {
        return $resource('/lawyer/api/empresas/:uid', {uid: '@uid'}, {
            update  : { method: 'PUT' }
        });
    })
;
