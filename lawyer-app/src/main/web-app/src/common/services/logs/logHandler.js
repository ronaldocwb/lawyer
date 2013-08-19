angular.module('lawyer.log', [])
    .factory('logFactory', ['$filter', function ($filter) {
        return function ($delegate) {

            var angularDate = $filter('date');

            return {
                 debug : function () {
                     var args = [angularDate(new Date(), 'dd/MM/yyyy - hh:mm:ss'), ' - '];
                     angular.forEach(arguments, function (i) {
                         args.push(i);
                     });
                     $delegate.debug.apply(null, args);
                 },
                 info : function () {
                     var args = [angularDate(new Date(), 'dd/MM/yyyy - hh:mm:ss'), ' - '];
                     angular.forEach(arguments, function (i) {
                         args.push(i);
                     });
                     $delegate.info.apply(null, args);
                 },
                 error: function () {
                     var args = [angularDate(new Date(), 'dd/MM/yyyy - hh:mm:ss'), ' - '];
                     angular.forEach(arguments, function (i) {
                         args.push(i);
                     });
                     $delegate.error.apply(null, args);
                 },
                 warn : function () {
                     var args = [angularDate(new Date(), 'dd/MM/yyyy - hh:mm:ss'), ' - '];
                     angular.forEach(arguments, function (i) {
                         args.push(i);
                     });
                     $delegate.warn.apply(null, args);
                 },
                 remote : function () {
                     var args = [angularDate(new Date(), 'dd/MM/yyyy - hh:mm:ss'), ' - '];
                     angular.forEach(arguments, function (i) {
                         args.push(i);
                     });

                     // Criar servico para salvar no server se for necessario...

                     $delegate.info.apply(null, args);
                 }

            };
        };
    }])

    .config(['$provide', function ($provide) {
        $provide.decorator('$log', ['$delegate', 'logFactory', function ($delegate, logFactory) {
            return logFactory($delegate);
        }]);
    }])
;
