/**
 * @ngdoc object
 * @name lawyer.controllers
 * @description
 * Esse eh o modulo para carregar todos os controllers.
 *
 * Assim sao centralizadas a declaracoes de controllers aqui, ao inves de adicionar no {@link lawyer.app} module.
 *
 * @example
 * `angular.module('lawyer.controllers', [` <br>
 *    `'lawyer.home',`<br>
 *    `'lawyer.atividades.popup'`<br>
 *    `'lawyer.outro.controller...'`
 *    `]);`
 *
 */
angular.module('lawyer.controllers', [
    'lawyer.home',
    'lawyer.atividades.popup',
    'lawyer.empresas',
    'lawyer.empresas.cadastro',
    'lawyer.empresas.edicao',
    'lawyer.empresas.listar',
    'lawyer.areasAtuacao',
    'lawyer.areasAtuacao.listar',
    'lawyer.areasAtuacao.cadastro'
]);
