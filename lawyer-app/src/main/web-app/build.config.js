/**
 * This file/module contains all configuration for the build process.
 */
module.exports = {
    /**
     * NÂO ALETRAR A PASTA BIN
     * A PASTA BUILT È O CAMINHO DO WAR EXPLODED.
     */
    test_dir: 'build',
//    build_dir: '../../../../lawyer-web/target/lawyer.war/secure',
//    compile_dir: '../../../../lawyer-web/src/main/webapp/secure',
    build_dir: '../../../../lawyer-web/src/main/webapp/secure',
    compile_dir: '../../../../lawyer-web/src/main/webapp/secure_compilado',

    app_files: {
        js: [ 'src/**/*.js', '!src/**/*.spec.js' ],
        jsunit: [ 'src/**/*.spec.js' ],

        atpl: [ 'src/app/**/*.tpl.html' ],
        ctpl: [ 'src/common/**/*.tpl.html' ],

        html: [ 'src/index.html' ],
        less: 'src/less/main.less'
    },


    vendor_files: {
        js: [
            'vendor/angular/angular.js',
			'vendor/angular-animate/angular-animate.js',
			'vendor/angular-cookies/angular-cookies.js',
			'vendor/angular-mocks/angular-mocks.js',
			'vendor/angular-resource/angular-resource.js',
			'vendor/angular-route/angular-route.js',
			'vendor/angular-i18n/angular-locale_pt.js',
            'vendor/angular-bootstrap/ui-bootstrap-tpls.js',
            'vendor/placeholders/angular-placeholders-0.0.1-SNAPSHOT.min.js',
            'vendor/angular-ui-router/release/angular-ui-router.js',
            'vendor/angular-ui-utils/modules/route/route.js',
            'vendor/angular-ui-utils/modules/mask/mask.js',
            'vendor/jquery/jquery.min.js',
            'vendor/noty/js/noty/jquery.noty.js',
            'vendor/noty/js/noty/layouts/top.js',
            'vendor/noty/js/noty/layouts/topRight.js',
            'vendor/noty/js/noty/layouts/bottom.js',
            'vendor/noty/js/noty/themes/default.js',
        ],
        css: [
            'vendor/animate.css/animate.css'
        ]
    }
};
