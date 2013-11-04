angular.module('lawyer.Advocacia', [
        'ngResource'
    ])

    .factory('Advocacia', function ($resource) {
        return $resource('/lawyer/api/advocacia/:uid', {uid: '@uid'}, {
            update  : { method: 'PUT' }
        });
    })
;
