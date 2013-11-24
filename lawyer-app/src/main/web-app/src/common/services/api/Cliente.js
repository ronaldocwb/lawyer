angular.module('lawyer.Cliente', [
        'ngResource'
    ])

    .factory('Cliente', function ($resource) {
        return $resource('/lawyer/api/clientes/:uid', {uid: '@uid'}, {
            update  : { method: 'PUT' }
        });
    })
;
