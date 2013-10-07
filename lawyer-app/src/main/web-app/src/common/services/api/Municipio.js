angular.module('lawyer.Municipio', [
        'ngResource'
    ])

    .factory('Municipio', ['$resource', function ($resource) {

        return $resource("/lawyer/api/municipios/:uid", {});

    }])
;

