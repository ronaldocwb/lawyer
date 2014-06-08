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
    'lawyer.agenda',
    'lawyer.atividades.popup',
    'lawyer.areasAtuacao',
    'lawyer.areasAtuacao.listar',
    'lawyer.areasAtuacao.cadastro',
    'lawyer.areasAtuacao.edicao',
    'lawyer.assuntos',
    'lawyer.assuntos.listar',
    'lawyer.assuntos.cadastro',
    'lawyer.atividades',
    'lawyer.atividades.cadastro',
    'lawyer.atividades.edicao',
    'lawyer.atividades.listar',
    'lawyer.configuracoes',
    'lawyer.configuracoes.advocacia',
    'lawyer.configuracoes.conta',
    'lawyer.contatos',
    'lawyer.contatos.listar',
    'lawyer.painel',
    'lawyer.empresas.cadastro',
    'lawyer.empresas.edicao',
    'lawyer.empresas.listar',
    'lawyer.pessoas.listar',
    'lawyer.pessoas.cadastro',
    'lawyer.pessoas.edicao',
    'lawyer.usuarios',
    'lawyer.usuarios.cadastro',
    'lawyer.usuarios.listar',
    'lawyer.configuracoes.conta'
]);
