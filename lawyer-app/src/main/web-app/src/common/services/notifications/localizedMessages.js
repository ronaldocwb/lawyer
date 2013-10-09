angular.module('services.localizedMessages', [])
    .factory('localizedMessages', ['$interpolate', 'i18n.messages', function ($interpolate, i18nMessages) {

        var handleNotFound = function (msg, msgKey) {
            return msg || '?' + msgKey + '?';
        };

        return {
            get: function (msgKey, interpolateParams) {
                var msg = i18nMessages[msgKey];
                if (msg) {
                    return $interpolate(msg)(interpolateParams);
                } else {
                    return handleNotFound(msg, msgKey);
                }
            }
        };
    }]);