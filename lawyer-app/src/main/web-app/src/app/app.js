angular.module('lawyer', [
        'templates-app',
        'templates-common',
        'lawyer.home',
        'ui.state',
        'ui.route'
    ])
    .config(['$urlRouterProvider', '$routeProvider', '$locationProvider', '$httpProvider', function ($urlRouterProvider, $routeProvider, $locationProvider, $httpProvider) {
        $urlRouterProvider.otherwise('/home');
        var interceptor = ['$location', '$q', function ($location, $q) {

            function success(response) {
                return response;
            }
            function error(response) {

                if (response.status === 401) {
                    $location.path('/login');
                    return $q.reject(response);
                }
                else {
                    return $q.reject(response);
                }
            }
            return function (promise) {
                return promise.then(success, error);
            };
        }];

        $httpProvider.responseInterceptors.push(interceptor);
    }])

    .run(function run() {
        // TODO implementar version control aqui.
    })

    .controller('AppCtrl', ['$scope', '$location', function AppCtrl($scope, $location) {

    }]);

