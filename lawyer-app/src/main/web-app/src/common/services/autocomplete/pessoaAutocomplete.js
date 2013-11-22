angular.module('pessoaAutocomplete', [])

    .factory('pessoaAutocomplete', ['$http', function ($http) {

        var lastPessoas = [];
        var lastQueryPessoas = null;
        return {
            query: function (value) {
                if (value.toLowerCase().indexOf(lastQueryPessoas) !== -1 && lastPessoas.length <= 8) {
                    return lastPessoas;
                }
                lastQueryPessoas = value.toLowerCase();
                return $http.get('/lawyer/api/pessoas?q=' + value + '&page=0&limit:8')
                    .then(function (result) {
                        lastPessoas = result.data.content;
                        return lastPessoas;
                    });
            }
        };
    }])

;

