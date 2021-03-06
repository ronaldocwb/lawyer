package br.com.lawyer.core.repository.predicates;

import br.com.lawyer.core.entity.QResponsavel;
import br.com.lawyer.core.util.StringUtils;
import com.mysema.query.types.Predicate;

/**
 * @author Deividi
 * @since 16/11/2013
 */
public class ResponsavelPredicate {

    public static Predicate isNomePessoaLike(final String nome) {
        if (StringUtils.isBlank(nome)) {
            return null;
        }
        QResponsavel responsavel = QResponsavel.responsavel;
        return responsavel.pessoa.nome.containsIgnoreCase(nome).or(responsavel.setor.nome.containsIgnoreCase(nome));
    }
}
