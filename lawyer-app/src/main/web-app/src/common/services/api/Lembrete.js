angular.module('lawyer.Lembrete', [
        'ngResource'
    ])

    .factory('Lembrete', function ($resource) {
        return $resource('/lawyer/api/lembretes/:uid/:batch', {uid: '@uid'}, {
            update  : { method: 'PUT' },
            removeBatch : { params : {batch : 'batch'}, method: 'POST', isArray:true}
        });
    })
;
