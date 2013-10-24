angular.module('lawyer.Advogado', [
        'ngResource'
    ])

    .factory('Advogado', function ($resource) {
        return $resource('/lawyer/api/advogados/:uid', {uid: '@uid'}, {
            update  : { method: 'PUT' }
        });
    })
;