package br.com.lawyer.core.repository.predicates;

import br.com.lawyer.core.entity.QSetor;
import br.com.lawyer.core.util.StringUtils;
import com.mysema.query.types.Predicate;

/**
 * @author Deividi
 * @since 16/11/2013
 */
public class SetorPredicate {

    public static Predicate nomeIsLike(final String searchTerm) {
        if (StringUtils.isBlank(searchTerm)) {
            return null;
        }
        QSetor setor = QSetor.setor;
        return setor.nome.containsIgnoreCase(searchTerm);
    }
}
