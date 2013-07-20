angular.module('lawyer.offline', [])

    .directive('offline', ['ConnectionStatus', function (ConnectionStatus) {
       return {
           restrict: 'E',

           link : function (scope, elem, attr) {
               elem.html();
               scope.$on('ConnectionStatus.CHANGE', function (event, status) {
                   var current = status === true ? 'ONLINE' : 'OFFLINE';
                   elem.html('<div> Voce esta ' + current + ' gordao.</div>');
               });
           }
        };
    }])
;

