angular.module('lawyer.header', [])
    .directive('header', [function () {
        var itens = ['painel', 'contatos', 'assuntos', 'areasAtuacao', 'advogados', 'atividades', 'agenda'];
        return {
            restrict : 'E',
            replace : true,
            controller : 'HeaderController',
            templateUrl : 'directives/menus/header/header.tpl.html',
            link : function (scope, element, attrs) {
                scope.abas = {};

                angular.forEach(itens, function(field) {
                    scope.abas[field] = false;
                });

                // Desativa o $watch que rodava a cada segundo e so altera a view quando receber o evento de troca de tela.
                scope.$on('$stateChangeSuccess', function (event, toState) {
                    for (var field in scope.abas) {
                        scope.abas[field] = false;
                    }
                    var parentState = toState.name.split('.');
                    switch (parentState[0]) {
                        case 'painel' :         scope.abas.painel   = true; break;
                        case 'empresas' :       scope.abas.contatos = true; break;
                        case 'pessoas' :        scope.abas.contatos = true; break;
                        case 'areasAtuacao' :   scope.abas.areasAtuacao = true; break;
                        case 'advogados' :      scope.abas.advogados = true; break;
                        case 'atividades' :     scope.abas.atividades = true; break;
                        case 'agenda' :     scope.abas.agenda = true; break;
                    }
                });
            }
        };
    }])

    .controller('HeaderController', ['$scope', '$location', 'breadcrumbs', 'notifications', 'httpRequestTracker',
        function ($scope, $location, breadcrumbs, notifications, httpRequestTracker) {
            $scope.location = $location;
            $scope.breadcrumbs = breadcrumbs;

            $scope.home = function () {
                $location.path('/painel');
            };

            $scope.isNavbarActive = function (navBarPath) {
                return navBarPath === breadcrumbs.getFirst().name;
            };

            $scope.hasPendingRequests = function () {
                return httpRequestTracker.hasPendingRequests();
            };
        }]);