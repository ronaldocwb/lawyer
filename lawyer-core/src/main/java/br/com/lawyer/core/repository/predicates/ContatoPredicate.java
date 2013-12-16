package br.com.lawyer.core.repository.predicates;

import br.com.lawyer.core.entity.QContato;
import br.com.lawyer.core.entity.enumerated.TipoContato;
import br.com.lawyer.core.util.StringUtils;
import com.mysema.query.types.Predicate;

/**
 * @author Deividi
 * @since 22/11/2013
 */
public class ContatoPredicate {

    public static Predicate buscaPorTipo(String tipo) {
        if (StringUtils.isBlank(tipo)) {
            return null;
        }
        QContato contato = QContato.contato;

        return contato.tipoContato.eq(TipoContato.valueOf(tipo));
    }

    public static Predicate buscarPorEmpresasLike (String query) {
        QContato contato = QContato.contato;

        if (StringUtils.isBlank(query)) {
            return contato.empresa.isNotNull();
        }
        return contato.empresa.nomeFantasia.containsIgnoreCase(query).or(contato.empresa.razaoSocial.containsIgnoreCase(query));

    }

    public static Predicate buscarPorPessoasLike (String query) {
        QContato contato = QContato.contato;

        if (StringUtils.isBlank(query)) {
            return contato.pessoa.isNotNull();
        }

        return contato.pessoa.nome.containsIgnoreCase(query).or(contato.pessoa.nome.containsIgnoreCase(query));
    }
}
