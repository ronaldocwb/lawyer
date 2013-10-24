angular.module('lawyer.AreaAtuacao', [
        'ngResource'
    ])

    .factory('AreaAtuacao', function ($resource) {
        return $resource('/lawyer/api/areasAtuacao/:uid',  {uid : '@uid'}, {
            update  : { method: 'PUT'}
        });
    })
;
