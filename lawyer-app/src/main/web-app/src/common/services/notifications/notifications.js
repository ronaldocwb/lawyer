angular.module('services.notifications', ['services.notificationsHandler', 'services.localizedMessages'])

    .factory('notifications', ['localizedMessages', 'notificationsHandler', function (localizedMessages, notificationsHandler) {

        var prepareNotification = function (mensagem, type, layout, timeout, interpolateParams, otherProperties) {
            return angular.extend({
                message: localizedMessages.get(mensagem, interpolateParams),
                type: type,
                layout : layout,
                timeout : timeout
            }, otherProperties);
        };

        return {
            size : function () {
                return notificationsHandler.size();
            },
            pushSticky : function (mensagem, type, layout, timeout, interpolateParams, otherProperties) {
                return notificationsHandler.pushSticky(prepareNotification(mensagem, type, layout, false, interpolateParams, otherProperties));
            },

            pushForCurrentRoute : function (mensagem, type, layout, timeout, interpolateParams, otherProperties) {
                return notificationsHandler.pushForCurrentRoute(prepareNotification(mensagem, type, layout, timeout, interpolateParams, otherProperties));
            },

            pushForNextRoute : function (mensagem, type, layout, timeout, interpolateParams, otherProperties) {
                return notificationsHandler.pushForNextRoute(prepareNotification(mensagem, type, layout, timeout, interpolateParams, otherProperties));
            },

            getCurrent : function () {
                return notificationsHandler.getCurrent();
            },

            remove : function (notification) {
                return notificationsHandler.remove(notification);
            }
        };

    }]);