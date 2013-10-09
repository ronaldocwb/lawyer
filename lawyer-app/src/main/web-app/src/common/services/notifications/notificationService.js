angular.module('lawyer.notifications', [])
    .factory('notificationService', [function () {
        var doNotCloseNotifications = [];
        var canCloseNotifications = [];
        return {
            add: function (notification) {
                var box = noty({text: notification.message, type: notification.type, layout: notification.layout,
                    timeout: notification.timeout === false ? notification.timeout : notification.timeout ? notification.timeout : 2000});
                if (box.options.timeout === false) {
                    doNotCloseNotifications.push(box.options.id);
                } else {
                    canCloseNotifications.push(box.options.id);
                }
            },
            closeAll : function () {
                $.noty.closeAll();
            },
            closeForRoute : function () {
                angular.forEach(canCloseNotifications, function (id) {
                    if ($.noty.get(id)) {
                        $.noty.close(id);
                    }
                });
            },
            getNotifications : function () {
                return canCloseNotifications.concat(doNotCloseNotifications);
            },
            getFixedNotifications : function () {
                return doNotCloseNotifications;
            },
            closeById : function (id) {
                if ($.noty.get(id)) {
                    $.noty.close(id);
                }
            }
        };
    }])
;

