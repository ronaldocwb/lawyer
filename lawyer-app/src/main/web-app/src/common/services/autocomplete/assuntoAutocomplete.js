angular.module('assuntoAutocomplete', [])

    .factory('assuntoAutocomplete', ['$http', function ($http) {

        var lastResult = [];
        var lastQuery = null;
        return {
            query: function (value) {
                if (value.toLowerCase().indexOf(lastQuery) !== -1 && lastResult.length <= 8) {
                    return lastResult;
                }
                lastQuery = value.toLowerCase();
                return $http.get('/lawyer/api/assuntos?q=' + value + '&page=0&limit=8')
                    .then(function (results) {
                        lastResult = results.data.content;
                        return lastResult;
                    });
            }
        };
    }])

;

