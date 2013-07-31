angular.module('services.i18nNotifications', ['services.notifications', 'services.localizedMessages'])

    .factory('i18nNotifications', ['localizedMessages', 'notifications', function (localizedMessages, notifications) {

        var prepareNotification = function (msgKey, type, interpolateParams, otherProperties) {
            return angular.extend({
                message: localizedMessages.get(msgKey, interpolateParams),
                type: type
            }, otherProperties);
        };

        return {
            pushSticky : function (msgKey, type, interpolateParams, otherProperties) {
                return notifications.pushSticky(prepareNotification(msgKey, type, interpolateParams, otherProperties));
            },

            pushForCurrentRoute : function (msgKey, type, interpolateParams, otherProperties) {
                return notifications.pushForCurrentRoute(prepareNotification(msgKey, type, interpolateParams, otherProperties));
            },

            pushForNextRoute : function (msgKey, type, interpolateParams, otherProperties) {
                return notifications.pushForNextRoute(prepareNotification(msgKey, type, interpolateParams, otherProperties));
            },

            getCurrent : function () {
                return notifications.getCurrent();
            },

            remove : function (notification) {
                return notifications.remove(notification);
            }
        };

    }]);