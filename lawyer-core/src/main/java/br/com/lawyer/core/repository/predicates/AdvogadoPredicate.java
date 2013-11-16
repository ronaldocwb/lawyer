package br.com.lawyer.core.repository.predicates;

import br.com.lawyer.core.entity.QAdvogado;
import com.mysema.query.types.Predicate;

/**
 * @author Deividi
 * @since 16/11/2013
 */
public class AdvogadoPredicate {

    public static Predicate possuiPessoaUid(final String pessoaUid) {
        QAdvogado advogado = QAdvogado.advogado;
        return advogado.pessoa.uid.eq(pessoaUid);
    }
}
