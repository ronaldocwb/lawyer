package br.com.lawyer.core.repository.predicates;

import br.com.lawyer.core.entity.QAreaAtuacao;
import br.com.lawyer.core.util.LawyerStringUtils;
import com.mysema.query.types.Predicate;

/**
 * @author Deividi
 * @since 16/11/2013
 */
public class AreaAtuacaoPredicate {

    public static Predicate nomeIsLike(final String searchTerm) {
        if (LawyerStringUtils.isBlank(searchTerm)) {
            return null;
        }
        QAreaAtuacao areaAtuacao = QAreaAtuacao.areaAtuacao;
        return areaAtuacao.nome.containsIgnoreCase(searchTerm);
    }
}
