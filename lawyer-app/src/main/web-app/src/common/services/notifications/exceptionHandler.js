angular.module('services.exceptionHandler', ['services.notifications'])

    .factory('exceptionHandlerFactory', ['$injector', function ($injector) {
        return function ($delegate) {

            return function (exception, cause) {
                var notifications = $injector.get('notifications');

                $delegate(exception, cause);
                if ($.noty) {
                    notifications.pushError('error.fatal', 'error', {
                        exception: exception.message,
                        cause: cause
                    });
                }

            };
        };
    }])

    .config(['$provide', function ($provide) {
        $provide.decorator('$exceptionHandler', ['$delegate', 'exceptionHandlerFactory', function ($delegate, exceptionHandlerFactory) {
            return exceptionHandlerFactory($delegate);
        }]);
    }]);
