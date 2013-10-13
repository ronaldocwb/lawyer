angular.module('services.notificationsHandler', [ ])
    .factory('notificationsHandler', ['$rootScope', function ($rootScope) {

        var notifications = {
            'STICKY': [],
            'ROUTE_CURRENT': [],
            'ROUTE_NEXT': []
        };
        var addNotification = function (notificationsArray, notificationObj) {
            if (!angular.isObject(notificationObj)) {
                throw new Error("Apenas objetos podem ser adicionados ao notifications delegate");
            }
            notificationsArray.push(notificationObj);
        };

        $rootScope.$on('$stateChangeSuccess', function () {
            $rootScope.$broadcast('noty.close.route.notifications');
            notifications.ROUTE_CURRENT.length = 0;
            notifications.ROUTE_CURRENT = angular.copy(notifications.ROUTE_NEXT);
            notifications.ROUTE_NEXT.length = 0;
            $rootScope.$broadcast('noty.add.notifications', notifications.ROUTE_CURRENT);
        });

        return {
            pushSticky: function (notification) {
                $rootScope.$broadcast('noty.add.sticky.notification', notification);
                addNotification(notifications.STICKY, notification);
            },

            pushForCurrentRoute: function (notification) {
                $rootScope.$broadcast('noty.add.notification', notification);
            },

            pushError: function (notification) {
                $rootScope.$broadcast('noty.add.error.notification', notification);
            },

            pushForNextRoute: function (notification) {
                addNotification(notifications.ROUTE_NEXT, notification);
            }

        };

    }]);