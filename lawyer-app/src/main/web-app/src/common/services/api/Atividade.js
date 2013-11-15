angular.module('lawyer.Atividade', [
        'ngResource'
    ])

    .factory('Atividade', function ($resource) {
        return $resource('/lawyer/api/atividades/:uid', {uid: '@uid'}, {
            update  : { method: 'PUT' }
        });
    })
;
