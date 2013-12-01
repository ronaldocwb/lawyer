angular.module('services.notifications', ['services.notificationsHandler', 'services.localizedMessages'])

    .factory('notifications', ['localizedMessages', 'notificationsHandler', function (localizedMessages, notificationsHandler) {

        var prepareNotification = function (mensagem, type, layout, timeout, interpolateParams, otherProperties) {
            return angular.extend({
                text: localizedMessages.get(mensagem, interpolateParams),
                type: type,
                layout : layout,
                timeout : timeout
            }, otherProperties);
        };

        return {
            pushSticky : function (mensagem, type, interpolateParams, layout, timeout, otherProperties) {
                return notificationsHandler.pushSticky(prepareNotification(mensagem, type, layout, timeout, interpolateParams, otherProperties));
            },

            pushError : function (mensagem, type, interpolateParams, layout, timeout, otherProperties) {
                return notificationsHandler.pushError(prepareNotification(mensagem, type, layout, timeout, interpolateParams, otherProperties));
            },

            pushForCurrentRoute : function (mensagem, type, interpolateParams, layout, timeout, otherProperties) {
                return notificationsHandler.pushForCurrentRoute(prepareNotification(mensagem, type, layout, timeout, interpolateParams, otherProperties));
            },

            pushLoginExpirou : function () {
                var notification = {
                    text : 'Sua sessão expirou, realize um novo login.',
                    type : "information",
                    modal : true,
                    layout : "center"
                };
                return notificationsHandler.pushLoginExpirou(notification);
            },

            pushForNextRoute : function (mensagem, type, interpolateParams, layout, timeout, otherProperties) {
                return notificationsHandler.pushForNextRoute(prepareNotification(mensagem, type, layout, timeout, interpolateParams, otherProperties));
            },
            pushCompletarCadastro : function (mensagem, interpolateParams, callback, item) {
                var type = "information";
                var layout = "topRight";
                var timeout = 3000;
                var properties = {
                    callback : callback,
                    item : item
                };
                return notificationsHandler.pushCompletarCadastro(prepareNotification(mensagem, type, layout, timeout, interpolateParams, properties));
            }
        };

    }]);