angular.module('lawyer.noty.container', [])
    .directive("notyContainer", function () {

        $.noty.defaults.layout = "top";
        $.noty.defaults.timeout = 3000;
        $.noty.defaults.maxVisible = 5;
        $.noty.defaults.modal = false;
        $.noty.defaults.closeWith = ["click"];

        return {
            restrict: "EA",
            replace: true,
            template: '<div class="custom_container"></div>',
            transclude: true,
            scope: {
                completar: '&',
                notification: '='
            },
            link: function (scope, elem, attrs) {

                scope.$watch('notification', function (newValue, oldValue) {
                    if (newValue === oldValue) {
                        return;
                    }
                    var box = $('.custom_container').
                        noty({
                            text: newValue.text,
                            buttons: [
                                {addClass: 'btn btn-success', text: 'Completar Cadastro', onClick: function ($noty) {
                                    event.preventDefault();
                                    $noty.close();
                                    scope.completar();
                                }
                                }
                            ]
                        });
                    setTimeout(function () {
                        box.close();
                    }, 3000);
                });

            }
        };
    })
;
