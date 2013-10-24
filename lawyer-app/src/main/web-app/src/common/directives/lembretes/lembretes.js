angular.module('lawyer.lembretes', [
        'lawyer.Lembrete',
        'ngAnimate'])

    .directive('lembretes', [function () {
        return {
            restrict: 'E',
            replace: true,
            controller: 'LembretesController',
            templateUrl: 'directives/lembretes/lembretes.tpl.html',
            link: function (scope, element, attrs) {
            }
        };
    }])
    .controller('LembretesController', ['$scope', '$interval', 'Lembrete', '$timeout', function ($scope, $interval, Lembrete, $timeout) {

        $scope.lembretes = [];//Lembrete.get();

        $scope.novoLembrete = {
            texto: '',
            uid : 3
        };

        $scope.addLembrete = function () {
            $scope.lembretes.push(angular.copy($scope.novoLembrete));
            $scope.novoLembrete = {
                texto: '',
                uid : 3
            };
        };

        $scope.removeLembrete = function (index) {
            $scope.lembretes.splice(index, 1);
        };

        $scope.clearAll = function () {
            var interval = $interval(function () {
                if ($scope.lembretes.length === 0) {
                    $interval.cancel(interval);
                    return;
                }
                Lembrete.remove($scope.lembretes.length-1);
                $scope.lembretes.pop();
            }, 50);
        };

        $scope.clearFinished = function () {
            angular.forEach($scope.lembretes, function (item) {
                /** @namespace item.finalizado */
                if (item.finalizado === true) {
                    $scope.lembretes.splice($scope.lembretes.indexOf(item), 1);
                    Lembrete.remove(item);
                }
            });
        };
    }])
;

