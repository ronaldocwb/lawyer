package br.com.lawyer.web.delegate;

import br.com.lawyer.web.vo.MunicipioVO;
import org.springframework.data.domain.Page;

/**
 * @author Deividi
 * @since 07/10/2013
 */
public interface IMunicipioDelegate {

    public Page<MunicipioVO> buscaPorNome (String query, int page, int limit);
}
