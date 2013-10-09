angular.module('lawyer.app.notifications', [])
    .directive('appNotifications', [function () {
        return {
            restrict : 'E',
            replace : true,
            controller: 'NotificationsController',
            templateUrl : 'directives/notifications/appNotifications.tpl.html',
            link : function (scope, elem, attrs, controller) {
            }
        };
    }])

    .controller('NotificationsController', ['$scope', '$log', 'i18nNotifications', '$timeout', function ($scope, $log, i18nNotifications, $timeout) {

        $scope.i18nNotifications = i18nNotifications;

        $scope.removeNotification = function (notification) {
            i18nNotifications.remove(notification);
        };

        $scope.$on('$stateChangeError', function(event, current, previous, rejection){
            i18nNotifications.pushForCurrentRoute('errors.route.changeError', 'error', {}, {rejection: rejection});
        });

    }])

;
