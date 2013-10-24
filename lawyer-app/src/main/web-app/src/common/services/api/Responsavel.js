angular.module('lawyer.Responsavel',
        ['ngResource']
    )
    .factory('Responsavel', ['$resource', function ($resource) {
        return $resource('/lawyer/api/responsaveis/:uid', {uid: '@uid'}, {
            update  : { method: 'PUT' }
        });
    }])
;

