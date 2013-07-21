angular.module('lawyer.offline', [])

    .directive('offline', ['ConnectionStatus', function (ConnectionStatus) {
       return {
           restrict: 'E',
           template : '<div modal="shouldBeOpen" close="close()" options="opts"><div class="modal-header"><h3>Sem conexão</h3></div><div class="modal-body">' +
               '<p>' +
               'Sua conexao nao esta ativa. Para utilizar o aplicativo, por favor verifique sua conexao e aguarde.' +
               '</p>' +
               '</div>' +
               '<div class="modal-footer">' +
               '</div></div></div>'
        };
    }])
;

