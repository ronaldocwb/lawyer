angular.module('services.httpRequestTracker', [])

    .factory('httpRequestTracker', ['$http', function ($http) {

        return {
            hasPendingRequests: function () {
                console.log($http.pendingRequests.length);
                console.log('requests');
                return $http.pendingRequests.length > 0;

            }
        };
    }]);