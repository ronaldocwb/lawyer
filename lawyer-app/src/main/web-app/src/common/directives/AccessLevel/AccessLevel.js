angular.module('lawyer.accessLevel', ['lawyer.auth'])

    /**
    * Directive para esconder elementos no HTML que sejam de alguma permissao especifica e que o usuario nao tenha ela.
     * Essa validacao devera ser realizada no server tambem, por motivos obvios
    */
    .directive('accessLevel', ['auth', function (auth) {

        return {
            restrict: 'A',
            link: function ($scope, element, attrs) {

                var prevDisp = element.css('display');
                var accessLevel = '';

                attrs.$observe('accessLevel', function (al) {
                    if (al) {
                        accessLevel = al;
                    }
                    updateCSS();
                });

                /**
                 * Talvez de para melhorar isso...
                 */
                function updateCSS() {
                    if (accessLevel) {
                        if (!auth.authorize(accessLevel)) {
                            element.css('display', 'none');
                        } else {
                            element.css('display', prevDisp);
                        }
                    }
                }
            }
        };
    }]);