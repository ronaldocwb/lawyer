angular.module('lawyer.notifications', [])
    .factory('notificationService', [function () {
        var doNotCloseNotifications = [];
        var canCloseNotifications = [];
        return {
            add: function (notification) {
                var params = {
                    timeout : notification.timeout === false ? notification.timeout : notification.timeout ? notification.timeout : 2000,
                    layout : notification.layout ? notification.layout : 'top',
                    type : notification.type ? notification.type : 'information'
                };

                var box = noty({text: notification.message, type: params.type, layout: params.layout, timeout: params.timeout});

                angular.noop(box.options.timeout === false ? doNotCloseNotifications.push(box.options.id) : canCloseNotifications.push(box.options.id));
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

