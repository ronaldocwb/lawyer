angular.module('AreaAtuacaoResource', [
        'ngResource'
    ])

    .factory('AreaAtuacaoResource', function ($resource) {
        return $resource('/lawyer/api/areasAtuacao/:id?q=:name&page=:page', {}, {
            query   : { method: 'GET',      params: {id: ''}, isArray: false },
            one     : { method: 'GET',      params: {id: ''}, isArray: false },
            save    : { method: 'POST',     params: {id: ''}, isArray: false },
            update  : { method: 'PUT',      params: {id: ''}, isArray: false },
            delete  : { method: 'DELETE',   params: {id: ''}, isArray: false },
            search  : { method: 'GET',      params: {id: '', name: '@name', page: 0}, isArray: false }
        });
    })
;