angular.module('lawyer.header', [])
    .directive('header', [function () {
        return {
            restrict : 'E',
            replace : true,
            controller : 'HeaderController',
            templateUrl : 'directives/menus/header/header.tpl.html',
            link : function (scope, element, attrs) {
            }
        };
    }])

    .controller('HeaderController', ['$scope', '$location', 'breadcrumbs', 'notifications', 'httpRequestTracker',
        function ($scope, $location, breadcrumbs, notifications, httpRequestTracker) {
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