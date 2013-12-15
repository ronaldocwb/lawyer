angular.module('lawyer.Contato', [
        'ngResource'
    ])

    .factory('Contato', function ($resource) {
        return $resource('/lawyer/api/contatos/:uid/:tipoContato/:contatoUid', {uid: '@uid'}, {
            update: { method: 'PUT' },
            getEmpresas: { params: { tipoContato: 'empresas' }, method: 'GET' },
            getPessoas: { params: { tipoContato: 'pessoas' }, method: 'GET' },
            getPessoaByUid: { params: { tipoContato: 'pessoas', contatoUid: '@pessoaUid' }, method: 'GET' },
            getEmpresaByUid: { params: { tipoContato: 'pessoas', contatoUid: '@empresaUid' }, method: 'GET' }
        });
    })
;
