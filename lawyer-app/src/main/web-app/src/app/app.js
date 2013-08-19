angular.module('lawyer', [
        'ngRoute',
		'ngAnimate',
		'ngLocale',
		'templates-app',
        'templates-common',
        'lawyer.home',
        'ui.state',
        'ui.route',
        'ui.bootstrap',
        'lawyer.connectionStatus',
        'lawyer.accessLevel',
        'services.i18nNotifications',
        'services.httpRequestTracker',
        'services.breadcrumbs',
        'i18n.Constants',
        'services.exceptionHandler',
        'lawyer.menus',
        'lawyer.log'
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

    .run(['auth', function run(auth) {
        auth.set();
    }])

    .controller('AppController', ['$scope', '$dialog', 'connectionStatus', '$timeout', function ($scope, $dialog, connectionStatus) {

        // Tratamento de usuario sem conexao ativa.
        connectionStatus.handle();
        var offlineDialog = null;

        $scope.$on('ConnectionStatus.CHANGE', function (event, status) {
            $scope.$apply(function () {
                if (status === false && !offlineDialog) {
                    offlineDialog = $dialog.dialog({
                        backdropFade: true,
                        dialogFade: true,
                        keyboard: false,
                        backdropClick: false
                    });
                    offlineDialog.open('templates/modalOffline/modalOffline.tpl.html');
                } else if (status === true && offlineDialog) {
                    offlineDialog.close();
                    offlineDialog = null;
                }
            });
        });

		}]);


