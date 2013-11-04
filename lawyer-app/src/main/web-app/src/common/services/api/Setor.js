angular.module('lawyer.Setor', [
        'ngResource'
    ])

    .factory('Setor', function ($resource) {
        return $resource('/lawyer/api/setores/:uid', {uid: '@uid'}, {
            update  : { method: 'PUT' }
        });
    })
;
