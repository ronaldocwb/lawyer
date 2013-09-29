angular.module('services.notifications', [])
    .factory('notifications', ['$rootScope', function ($rootScope) {

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
            return notificationObj;
        };

        $rootScope.$on('$stateChangeSuccess', function () {
            notifications.ROUTE_CURRENT.length = 0;
            notifications.ROUTE_CURRENT = angular.copy(notifications.ROUTE_NEXT);
            notifications.ROUTE_NEXT.length = 0;
        });

        return {
            getCurrent: function () {
                return [].concat(notifications.STICKY, notifications.ROUTE_CURRENT);
            },

            pushSticky: function (notification) {
                return addNotification(notifications.STICKY, notification);
            },

            pushForCurrentRoute: function (notification) {
                return addNotification(notifications.ROUTE_CURRENT, notification);
            },

            pushForNextRoute: function (notification) {
                return addNotification(notifications.ROUTE_NEXT, notification);
            },

            remove: function (notification) {
                angular.forEach(notifications, function (notificationsByType) {
                    var idx = notificationsByType.indexOf(notification);
                    if (idx > -1) {
                        notificationsByType.splice(idx, 1);
                    }
                });
            },
            removeAll: function () {
                angular.forEach(notifications, function (notificationsByType) {
                    notificationsByType.length = 0;
                });
            }
        };

    }]);