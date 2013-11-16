package br.com.lawyer.core.repository.predicates;

import br.com.lawyer.core.entity.QAdvogado;
import br.com.lawyer.core.util.LawyerStringUtils;
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

    public static Predicate buscaSePossuirNome(final String nome) {
        if (LawyerStringUtils.isBlank(nome)) {
            return null;
        }
        QAdvogado advogado = QAdvogado.advogado;
        return advogado.pessoa.nome.eq(nome);
    }
}
