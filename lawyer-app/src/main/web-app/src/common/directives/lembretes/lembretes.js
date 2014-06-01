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
            $scope.novoLembrete = {
                texto: ''
            };
            $scope.todo = Lembrete.save($scope.todo, function () {
                $scope.lembretes.push($scope.todo);
            });

        };

        $scope.removeLembrete = function (index) {
            var uid = $scope.lembretes[index].uid;
            $scope.lembretes.splice(index, 1);
            Lembrete.remove(uid);
        };

        $scope.updateLembrete = function ($item) {
            $scope.lembretes[$item].finalizado = !$scope.lembretes[$item].finalizado;
            Lembrete.update($scope.lembretes[$item]);
        };

        $scope.clearAll = function () {
            $scope.lembretes.length = 0;
            Lembrete.removeAll();
        };

    }])
;

