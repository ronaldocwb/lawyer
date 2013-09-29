angular.module('EmpresaResource', [
        'ngResource'
    ])

    .factory('EmpresaResource', function ($resource) {
        return $resource('/lawyer/api/empresas/:id', {}, {
            query   : { method: 'GET',      params: {id: ''}, isArray: false },
            one     : { method: 'GET',      params: {id: ''}, isArray: false },
            save    : { method: 'POST',     params: {id: ''}, isArray: false },
            update  : { method: 'PUT',      params: {id: ''}, isArray: false },
            delete  : { method: 'DELETE',   params: {id: ''}, isArray: false }
        });
    })
;
