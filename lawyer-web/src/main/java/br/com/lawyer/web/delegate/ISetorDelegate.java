package br.com.lawyer.web.delegate;

import br.com.lawyer.web.vo.SetorVO;
import org.springframework.data.domain.Page;

/**
 * @author Deividi
 * @since 03/11/2013
 */
public interface ISetorDelegate {
    Page<SetorVO> findAllByNome (String query, int page, int limit);

    SetorVO salvar (SetorVO setorVO);

}
