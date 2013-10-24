angular.module('lawyer.Lembrete', [
        'ngResource'
    ])

    .factory('Lembrete', function ($resource) {
        return $resource('/lawyer/api/lembretes/:uid', {uid: '@uid'}, {});
    })
;
