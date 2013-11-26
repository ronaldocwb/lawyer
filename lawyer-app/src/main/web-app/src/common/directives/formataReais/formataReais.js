angular.module('formataReais', [])
    .directive('formataReais', [function () {
        return {
            restrict : 'EA',
            replace : true,
            template : '<span data-ng-bind="valorFormatado"></span>',
            scope : {
                valor : '='
            },
            link : function (scope) {
                scope.valorFormatado = scope.valor.toFixed(2).replace('.', ',');
            }
        };
    }])
;

