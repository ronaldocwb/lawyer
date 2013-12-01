package br.com.lawyer.core.repository.predicates;

import br.com.lawyer.core.entity.QAssunto;
import br.com.lawyer.core.util.StringUtils;
import com.mysema.query.types.Predicate;

/**
 * @author Deividi
 * @since 15/11/2013
 */
public class AssuntoPredicate {

    public static Predicate nomeIsLike(final String searchTerm) {
        if (StringUtils.isBlank(searchTerm)) {
            return null;
        }
        QAssunto assunto = QAssunto.assunto;
        return assunto.nome.containsIgnoreCase(searchTerm);
    }

    public static Predicate clienteUidEqualsTo (String uid) {
        QAssunto assunto = QAssunto.assunto;
        return assunto.cliente.uid.eq(uid);
    }
}
