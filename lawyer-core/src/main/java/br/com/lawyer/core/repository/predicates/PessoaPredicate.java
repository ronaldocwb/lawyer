package br.com.lawyer.core.repository.predicates;

import br.com.lawyer.core.entity.QPessoa;
import br.com.lawyer.core.util.LawyerStringUtils;
import com.mysema.query.types.Predicate;

/**
 * @author Deividi
 * @since 16/11/2013
 */
public class PessoaPredicate {

    public static Predicate nomeIsLike(final String searchTerm) {
        if (LawyerStringUtils.isBlank(searchTerm)) {
            return null;
        }
        QPessoa pessoa = QPessoa.pessoa;
        return pessoa.nome.containsIgnoreCase(searchTerm);
    }
}
