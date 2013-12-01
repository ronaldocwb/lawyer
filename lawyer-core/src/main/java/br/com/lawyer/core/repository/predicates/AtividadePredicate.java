package br.com.lawyer.core.repository.predicates;

import br.com.lawyer.core.entity.QAtividade;
import br.com.lawyer.core.util.StringUtils;
import com.mysema.query.types.Predicate;

/**
 * @author Deividi
 * @since 27/11/2013
 */
public class AtividadePredicate {
    public static Predicate possuiNomeAssuntoLike (String query) {
        if (StringUtils.isBlank(query)) {
            return null;
        }
        QAtividade atividade = QAtividade.atividade;
        return atividade.assunto.nome.containsIgnoreCase(query);
    }
}
