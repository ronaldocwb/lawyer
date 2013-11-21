angular.module('formataHora', [])
    .directive('formataHora', [function () {
        return {
            restrict : 'EA',
            replace : true,
            template : '<span data-ng-bind="duracaoFormatada"></span>',
            scope : {
                duracao : '='
            },
            link : function (scope) {
                scope.duracaoFormatada = '';
                var horas = scope.duracao % 60;
                if (horas < 10) {
                    horas = '0' + horas;
                }
                var minutos = Math.round(scope.duracao / 60);
                if (minutos < 10) {
                    minutos = '0' + minutos;
                }

                scope.duracaoFormatada = horas + ':' + minutos;
            }
        };
    }])
;

