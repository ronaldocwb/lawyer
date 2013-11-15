angular.module('lawyer.noty', [])
    .directive("noty", ['$rootScope', '$timeout', function ($rootScope, $timeout) {

        $.noty.defaults.layout = "top";
        $.noty.defaults.timeout = 3000;
        $.noty.defaults.maxVisible = 5;
        $.noty.defaults.modal = false;
        $.noty.defaults.closeWith = ["click"];
        var doNotCloseNotifications = [];
        var canCloseNotifications = [];

        return {
            restrict: "A",
            replace : false,

            link: function (scope) {

                scope.$on('noty.add.completar.cadastro', function (event, notification) {
                    scope.addCompletarNotification(angular.extend(notification));
                });

                scope.$on('noty.close.route.notifications', function () {
                    scope.closeForRoute();

                });
                scope.$on('noty.add.notifications', function (event, notifications) {
                    scope.addAll(notifications);

                });
                scope.$on('noty.add.sticky.notification', function (event, notification) {
                    notification.timeout = false;
                    scope.add(angular.extend(notification));
                });

                scope.$on('noty.add.notification', function (event, notification) {
                    scope.add(angular.extend(notification));
                });

                scope.$on('noty.add.error.notification', function (event, notification) {
                    notification.timeout = false;
                    scope.addError(angular.extend(notification));
                });

                scope.add = function (notification) {
                    var box = noty(notification);
                    angular.noop(box.options.timeout === false ? doNotCloseNotifications.push(box.options.id) : canCloseNotifications.push(box.options.id));
                };

                scope.addCompletarNotification = function (notification) {
                    var buttons = [
                        {
                            addClass: 'btn btn-success', text: 'Completar Cadastro', onClick: function ($noty) {
                                $noty.close();
                                scope.emitCompletarCallback(notification);
                            }
                        }
                    ];
                    notification.buttons = buttons;
                    var box = noty(notification);
                    $timeout(function () {
                        $.noty.close(box.options.id);
                    }, notification.timeout);
                };

                scope.emitCompletarCallback = function (notification) {
                    $rootScope.$broadcast(notification.callback, notification.item);
                };

                scope.addError = function (notification) {
                    notification.layout = "bottom";
                    notification.type = "information";
                    var box = noty(notification);
                    angular.noop(box.options.timeout === false ? doNotCloseNotifications.push(box.options.id) : canCloseNotifications.push(box.options.id));
                };

                scope.addAll = function (notifications) {
                    angular.forEach(notifications, function (notification) {
                        scope.add(angular.extend(notification));
                    });
                };

                scope.closeAll = function () {
                    $.noty.closeAll();
                    canCloseNotifications = [];
                    doNotCloseNotifications = [];
                };

                scope.closeForRoute = function () {
                    angular.forEach(canCloseNotifications, function (id) {
                        if ($.noty.get(id)) {
                            $.noty.close(id);
                        }
                    });
                    canCloseNotifications = [];
                };

                scope.closeById = function (id) {
                    if ($.noty.get(id)) {
                        $.noty.close(id);
                    }
                    if (canCloseNotifications.indexOf(id) !== -1) {
                        canCloseNotifications.splice(canCloseNotifications.indexOf(id), 1);
                    } else {
                        doNotCloseNotifications.splice(doNotCloseNotifications.indexOf(id), 1);
                    }
                };
            }
        };
    }])
;
