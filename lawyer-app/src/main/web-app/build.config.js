/**
 * This file/module contains all configuration for the build process.
 */
module.exports = {
     /**
     * NÂO ALETRAR A PASTA BIN
     * A PASTA BUILT È O CAMINHO DO WAR EXPLODED.
     */
  //build_dir: 'build',
     build_dir: '../../../../lawyer-web/target/lawyer.war/secure',
  compile_dir: '../../../../lawyer-web/src/main/webapp/secure',

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
        'vendor/angular-unstable/angular.min.js',
        'vendor/angular-bootstrap/ui-bootstrap-tpls.min.js',
        'vendor/angular-resource-unstable/angular-resource.min.js',
        'vendor/placeholders/angular-placeholders-0.0.1-SNAPSHOT.min.js',
        'vendor/angular-ui-router/release/angular-ui-router.js',
        'vendor/angular-ui-utils/modules/route/route.js',
        'vendor/angular-cookies/angular-cookies.min.js'
    ],
    css: [
    ]
  },
};
