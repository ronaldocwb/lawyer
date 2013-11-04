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

        $scope.lembretes = Lembrete.query();

        $scope.novoLembrete = {
            texto: ''
        };

        $scope.addLembrete = function () {
            $scope.todo = angular.copy($scope.novoLembrete);
            $scope.todo = Lembrete.save($scope.todo);
            $scope.lembretes.push($scope.todo);
            $scope.novoLembrete = {
                texto: ''
            };
        };

        $scope.removeLembrete = function (index) {
            $scope.lembretes.splice(index, 1);
        };

        $scope.updateLembrete = function ($item) {
            $scope.lembretes[$item].finalizado = !$scope.lembretes[$item].finalizado;
            Lembrete.update($scope.lembretes[$item]);
        };

        $scope.clearAll = function () {
            var interval = $interval(function () {
                if ($scope.lembretes.length === 0) {
                    $interval.cancel(interval);
                    return;
                }
                $scope.lembretes.pop();
            }, 50);
            Lembrete.removeAll();
        };

    }])
;

