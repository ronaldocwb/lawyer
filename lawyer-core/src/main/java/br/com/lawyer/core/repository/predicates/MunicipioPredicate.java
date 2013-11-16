package br.com.lawyer.core.repository.predicates;

import br.com.lawyer.core.entity.common.QMunicipio;
import br.com.lawyer.core.util.LawyerStringUtils;
import com.mysema.query.types.Predicate;

/**
 * @author Deividi
 * @since 15/11/2013
 */
public class MunicipioPredicate {

    public static Predicate nomeIsLike(final String searchTerm) {
        if (LawyerStringUtils.isBlank(searchTerm)) {
            return null;
        }
        QMunicipio municipio = QMunicipio.municipio;
        return municipio.nmMunicipio.containsIgnoreCase(searchTerm);
    }
}
