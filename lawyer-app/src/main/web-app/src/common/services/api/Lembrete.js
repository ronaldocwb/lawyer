angular.module('lawyer.Lembrete', [
        'ngResource'
    ])

    .factory('Lembrete', function ($resource) {
        return $resource('/lawyer/api/lembretes/:uid/:all', {uid: '@uid'}, {
            update  : { method: 'PUT' },
            removeAll : { params : {all : 'all'}, method: 'DELETE'}
        });
    })
;
