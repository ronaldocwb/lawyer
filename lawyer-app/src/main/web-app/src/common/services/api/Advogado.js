angular.module('lawyer.Advogado', [
        'ngResource'
    ])

    .factory('Advogado', function ($resource) {
        return $resource('/lawyer/api/advogados/:uid/:pessoas/:pessoaUid', {uid: '@uid'}, {
            update  : { method: 'PUT' },
            buscarPorPessoaUid : {method: 'GET', params : {pessoas : 'pessoas', pessoaUid : '@pessoa.uid'} }
        });
    })
;