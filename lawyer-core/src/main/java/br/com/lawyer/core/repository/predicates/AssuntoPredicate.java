package br.com.lawyer.core.repository.predicates;

import br.com.lawyer.core.entity.QAssunto;
import br.com.lawyer.core.util.LawyerStringUtils;
import com.mysema.query.types.Predicate;

/**
 * @author Deividi
 * @since 15/11/2013
 */
public class AssuntoPredicate {

    public static Predicate nomeIsLike(final String searchTerm) {
        if (LawyerStringUtils.isBlank(searchTerm)) {
            return null;
        }
        QAssunto assunto = QAssunto.assunto;
        return assunto.nome.containsIgnoreCase(searchTerm);
    }
}
