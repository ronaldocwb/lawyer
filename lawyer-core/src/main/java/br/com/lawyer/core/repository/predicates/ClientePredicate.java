package br.com.lawyer.core.repository.predicates;

import br.com.lawyer.core.entity.QCliente;
import br.com.lawyer.core.util.StringUtils;
import com.mysema.query.types.Predicate;

/**
 * @author Deividi
 * @since 22/11/2013
 */
public class ClientePredicate {

    public static Predicate buscarPorNomeEmpresa(String query) {
        if (StringUtils.isBlank(query)) {
            return null;
        }
        QCliente cliente = QCliente.cliente;

        return cliente.empresa.nomeFantasia.containsIgnoreCase(query);
    }

    public static Predicate buscarPorNomePessoa(String query) {
        if (StringUtils.isBlank(query)) {
            return null;
        }
        QCliente cliente = QCliente.cliente;

        return cliente.pessoa.nome.containsIgnoreCase(query);
    }


}
