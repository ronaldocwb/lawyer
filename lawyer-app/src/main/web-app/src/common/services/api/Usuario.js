angular.module('lawyer.Usuario', [
        'ngResource'
    ])

    .factory('Usuario', function ($resource) {
        return $resource('/lawyer/api/usuarios/:uid/:optional', {uid: '@uid'}, {
            update  : { method: 'PUT' },
            updateSenha  : { params : {uid : '@token', optional:'senha'}, method: 'PUT' },
            current  : { params : {uid : 'atual'}, method: 'GET' }
        });
    })
;