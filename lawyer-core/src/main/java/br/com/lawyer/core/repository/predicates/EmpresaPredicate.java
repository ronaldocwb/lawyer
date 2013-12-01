package br.com.lawyer.core.repository.predicates;

import br.com.lawyer.core.entity.QEmpresa;
import br.com.lawyer.core.util.StringUtils;
import com.mysema.query.types.Predicate;

/**
 * @author Deividi
 * @since 16/11/2013
 */
public class EmpresaPredicate {

    public static Predicate nomeIsLike(final String searchTerm) {
        if (StringUtils.isBlank(searchTerm)) {
            return null;
        }
        QEmpresa empresa = QEmpresa.empresa;
        return empresa.nomeFantasia.containsIgnoreCase(searchTerm).or(empresa.razaoSocial.containsIgnoreCase(searchTerm));
    }
}
