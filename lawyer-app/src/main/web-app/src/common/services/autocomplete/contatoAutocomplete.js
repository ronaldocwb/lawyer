angular.module('contatoAutocomplete', [])

    .factory('contatoAutocomplete', ['$http', function ($http) {

        var lastResult = [];
        var lastQuery = null;
        return {
            queryClientes: function (value) {
                if (value.toLowerCase().indexOf(lastQuery) !== -1 && lastResult.length < 8) {
                    return lastResult;
                }
                lastQuery = value.toLowerCase();
                return $http.get('/lawyer/api/contatos?q=' + value + '&page=0&limit=8&tipo=cliente')
                    .then(function (results) {
                        var clientes = [];
                        angular.forEach(results.data.content, function (value) {
                            if (value.empresa) {
                                value.empresa.nome = value.empresa.nomeFantasia;
                            }
                            clientes.push(value.pessoa ? value.pessoa : value.empresa);
                        });
                        lastResult = clientes;
                        return lastResult;
                    });
            }
        };
    }])

;

