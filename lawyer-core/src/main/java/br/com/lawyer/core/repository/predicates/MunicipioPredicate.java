package br.com.lawyer.core.repository.predicates;

import br.com.lawyer.core.entity.common.QMunicipio;
import br.com.lawyer.core.util.StringUtils;
import com.mysema.query.types.Predicate;

/**
 * @author Deividi
 * @since 15/11/2013
 */
public class MunicipioPredicate {

    public static Predicate nomeIsLike(final String searchTerm) {
        if (StringUtils.isBlank(searchTerm)) {
            return null;
        }
        QMunicipio municipio = QMunicipio.municipio;
        return municipio.nmMunicipio.containsIgnoreCase(searchTerm);
    }
}
