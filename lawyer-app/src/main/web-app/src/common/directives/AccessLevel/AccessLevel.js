angular.module('AccessLevel', [])

    .directive('accessLevel', ['$rootScope', 'Auth', function ($rootScope, Auth) {

        return {
            restrict: 'A',
            replace: true,
            link: function (scope, element, attrs) {
                var prevDisp = element.css('display');
                $rootScope.$watch('user.role', function () {
                    if (!Auth.authorize(attrs.accessLevel)) {
                        element.css('display', 'none');
                    } else {
                        element.css('display', prevDisp);
                    }
                });
            }
        };

    }])
;

