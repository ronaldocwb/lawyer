angular.module('lawyer.header', [])

    .controller('HeaderController', ['$scope', '$location', '$route', 'security', 'breadcrumbs', 'notifications', 'httpRequestTracker',
        function ($scope, $location, $route, security, breadcrumbs, notifications, httpRequestTracker) {
            $scope.location = $location;
            $scope.breadcrumbs = breadcrumbs;

            $scope.isAuthenticated = security.isAuthenticated;
            $scope.isAdmin = security.isAdmin;

            $scope.home = function () {
                if (security.isAuthenticated()) {
                    $location.path('/dashboard');
                } else {
                    $location.path('/projectsinfo');
                }
            };

            $scope.isNavbarActive = function (navBarPath) {
                return navBarPath === breadcrumbs.getFirst().name;
            };

            $scope.hasPendingRequests = function () {
                return httpRequestTracker.hasPendingRequests();
            };
        }]);



