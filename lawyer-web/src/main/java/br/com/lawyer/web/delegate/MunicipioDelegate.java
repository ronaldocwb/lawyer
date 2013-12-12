package br.com.lawyer.web.delegate;

import br.com.lawyer.core.entity.common.Municipio;
import org.springframework.data.domain.Page;

/**
 * @author Deividi
 * @since 07/10/2013
 */
public interface MunicipioDelegate {

    public Page<Municipio> buscaPorNome (String query, int page, int limit);
}
