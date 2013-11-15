package br.com.lawyer.web.delegate;

import br.com.lawyer.web.vo.AssuntoVO;
import org.springframework.data.domain.Page;

/**
 * @author Deividi
 * @since 15/11/2013
 */
public interface IAssuntoDelegate {
    Page<AssuntoVO> findAssuntoPorNomeOuPagina (String nome, int page, int limit);

    AssuntoVO salvar (AssuntoVO assuntoVO);

    void deletar (String uid);

    AssuntoVO update (AssuntoVO assuntoVO, String uid);

    AssuntoVO findOne (String uid);

}
