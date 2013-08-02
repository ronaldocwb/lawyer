angular.module('lawyer.header', [])

    .controller('HeaderController', ['$scope', '$location', '$route', 'breadcrumbs', 'notifications', 'httpRequestTracker',
        function ($scope, $location, $route, breadcrumbs, notifications, httpRequestTracker) {
            $scope.location = $location;
            $scope.breadcrumbs = breadcrumbs;

            $scope.home = function () {
                $location.path('/dashboard');
            };

            $scope.isNavbarActive = function (navBarPath) {
                return navBarPath === breadcrumbs.getFirst().name;
            };

            $scope.hasPendingRequests = function () {
                return httpRequestTracker.hasPendingRequests();
            };
        }]);



