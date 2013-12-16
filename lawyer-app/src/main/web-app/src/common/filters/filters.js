angular.module('filters', [])
    .filter('capitalize', function () {
        return function (input) {
            return input ? input.charAt(0).toUpperCase() + input.slice(1).toLowerCase() : '';
        };
    })
    .filter('hora', function () {
        return function (input) {
            var horas = input % 60;
            if (horas < 10) {
                horas = '0' + horas;
            }
            var minutos = Math.round(input / 60);
            if (minutos < 10) {
                minutos = '0' + minutos;
            }

            return horas + ':' + minutos;

        };
    })
    .filter('reais', function () {
        return function (input) {
            console.log(input, 'inputs');

            return input ? input.toFixed(2).replace('.', ',') : '';
        };
    })

;