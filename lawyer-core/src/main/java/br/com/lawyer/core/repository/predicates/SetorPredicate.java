package br.com.lawyer.core.repository.predicates;

import br.com.lawyer.core.entity.QSetor;
import br.com.lawyer.core.util.LawyerStringUtils;
import com.mysema.query.types.Predicate;

/**
 * @author Deividi
 * @since 16/11/2013
 */
public class SetorPredicate {

    public static Predicate nomeIsLike(final String searchTerm) {
        if (LawyerStringUtils.isBlank(searchTerm)) {
            return null;
        }
        QSetor setor = QSetor.setor;
        return setor.nome.containsIgnoreCase(searchTerm);
    }
}
