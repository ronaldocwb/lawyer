/**
 * @ngdoc object
 * @name lawyer.log
 * @description
 * Override do módulo $log nativo do angularjs através do {@link lawyer.log:provide provide}.
 *
 * Essa implementação adiciona o horário que o log foi lançado.
 * Poderá salvar os logs em um server remoto através do methodo {@lawyer.log.remote}
 */
angular.module('lawyer.log', [])

    /**
     * @ngdoc function
     * @function
     * @name lawyer.log#provide
     * @description
     * Realiza o override da função $log via @link{logFactory#delegate}
     */
    .factory('logFactory', ['$filter', function ($filter) {
        return function ($delegate) {

            var angularDate = $filter('date');

            return {
                 debug : function () {
                     var args = [angularDate(new Date(), 'hh:mm:ss : ')];
                     angular.forEach(arguments, function (i) {
                         args.push(i);
                     });
                     $delegate.debug.apply(null, args);
                 },
                 info : function () {
                     var args = [angularDate(new Date(), 'hh:mm:ss : ')];
                     angular.forEach(arguments, function (i) {
                         args.push(i);
                     });
                     $delegate.info.apply(null, args);
                 },
                 error: function () {
                     var args = [angularDate(new Date(), 'hh:mm:ss : ')];
                     angular.forEach(arguments, function (i) {
                         args.push(i);
                     });
                     $delegate.error.apply(null, args);
                 },
                 warn : function () {
                     var args = [angularDate(new Date(), 'hh:mm:ss : ')];
                     angular.forEach(arguments, function (i) {
                         args.push(i);
                     });
                     $delegate.warn.apply(null, args);
                 },

                /**
                 * @ngdoc function
                 * @function
                 * @name lawyer.log#remote
                 * @description
                 * Salva o log atual no servidor.
                 * @param {string} string valor do log.
                 */
                 remote : function () {
                     var args = [angularDate(new Date(), 'hh:mm:ss : ')];
                     angular.forEach(arguments, function (i) {
                         args.push(i);
                     });

                     // Criar servico para salvar no server se for necessario...

                     $delegate.info.apply(null, args);
                 }

            };
        };
    }])

    /**
    * @ngdoc function
    * @name lawyer.log.provide
    * @description
    * Realiza o override da função $log via @link{logFactory#delegate}
    */
    .config(['$provide', function ($provide) {
        $provide.decorator('$log', ['$delegate', 'logFactory', function ($delegate, logFactory) {
            return logFactory($delegate);
        }]);
    }])
;
