package br.com.lawyer.core.repository.predicates;

import br.com.lawyer.core.entity.QPessoa;
import br.com.lawyer.core.util.StringUtils;
import com.mysema.query.types.Predicate;

/**
 * @author Deividi
 * @since 16/11/2013
 */
public class PessoaPredicate {

    public static Predicate nomeIsLike(final String searchTerm) {
        if (StringUtils.isBlank(searchTerm)) {
            return null;
        }
        QPessoa pessoa = QPessoa.pessoa;
        return pessoa.nome.containsIgnoreCase(searchTerm);
    }
}
