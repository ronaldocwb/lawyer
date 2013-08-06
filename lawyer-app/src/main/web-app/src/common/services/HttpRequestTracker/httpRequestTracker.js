angular.module('services.httpRequestTracker', [])

    .factory('httpRequestTracker', ['$http', function ($http) {

        return {
            hasPendingRequests: function () {
                return $http.pendingRequests.length > 0;
            }
        };
    }]);