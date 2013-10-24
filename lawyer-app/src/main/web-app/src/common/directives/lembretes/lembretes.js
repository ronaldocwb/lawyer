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

        $scope.items = [];//Lembrete.get();

        $scope.novoLembrete = {
            texto: '',
            uid : 3
        };

        $scope.add = function () {
            $scope.items.push(angular.copy($scope.novoLembrete));
            $scope.novoLembrete = {
                texto: '',
                uid : 3
            };
        };

        $scope.removeTodo = function (index) {
            $scope.items.splice(index, 1);
        };

        $scope.clearAll = function () {
            var interval = $interval(function () {
                if ($scope.items.length === 0) {
                    $interval.cancel(interval);
                    return;
                }
                Lembrete.remove($scope.items.length-1);
                $scope.items.pop();
            }, 50);
        };

        $scope.clearFinished = function () {
            angular.forEach($scope.items, function (item) {
                /** @namespace item.finalizado */
                if (item.finalizado === true) {
                    Lembrete.remove(item);
                    $scope.items.splice($scope.items.indexOf(item), 1);
                }
            });
        };
    }])
;

