angular.module('lawyer.menu.central', [
        'lawyer.dropdown.hover'
    ])
    .directive('menuCentral', [function () {
        var itens = ['painel', 'contatos', 'assuntos', 'areasAtuacao'];
        return {
            restrict: 'E',
            replace: true,
            templateUrl: 'directives/menus/central/central.tpl.html',
            link: function (scope, elem, attrs) {
                scope.abas = {};

                angular.forEach(itens, function(field) {
                    scope.abas[field] = false;
                });

                // Desativa o $watch que rodava a cada segundo e só altera a view quando receber o evento de troca de tela.
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
                    }
                });
            }
        };
    }])
;

