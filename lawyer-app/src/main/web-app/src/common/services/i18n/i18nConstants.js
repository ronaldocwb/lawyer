angular.module('i18n.Constants', [])
    .constant('i18n.messages', {
        'errors.route.changeError': 'Erro na rota de URL',
        'empresa.salva': "A empresa <strong>{{nome}}</strong> foi criada!",
        'empresa.salva.erro': "Erro ao salvar a empresa <strong>{{nome}}</strong>. =(",
        'empresa.alterada': "A empresa <strong>{{nome}}</strong> foi alterada!",
        'areaAtuacao.salva': "A área de atuação foi salva!",
        'fixo': "Aviso FIXO até vc clicar. Você pode usar <code> Atributos HTML aqui!!!</code>",
        'teste': "Aviso por 3 segundos.",
        'teste5': "Aviso por 5 segundos!",
        'error.fatal': "Ocorreu um erro inesperado na aplicação!!! Veja o log!!! :: Erro: {{exception}}",
        'pessoa.salva': "A pessoa com nome <strong>{{nome}}</strong> foi criada!",
        'pessoa.salva.erro': "Ocorreu um erro ao salvar a pessoa <strong>{{nome}}</strong>. =(",
        'pessoa.alterada': "A pessoa com nome <strong>{{nome}}</strong> foi alterada!"

    });
