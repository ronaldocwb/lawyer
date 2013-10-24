package br.com.lawyer.web.delegate;

import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.vo.ResponsavelVO;
import org.springframework.data.domain.Page;

/**
 * @author Deividi
 * @since 22/10/2013
 */
public interface IResponsavelDelegate {
    Page<ResponsavelVO> findResponsavelPorPagina (String query, String field, int page, int limit) throws BusinessException;

    ResponsavelVO salvar (ResponsavelVO pessoaVO);

    void deletar (String uid);

    ResponsavelVO atualizar (ResponsavelVO pessoaVO, String uid);

    ResponsavelVO buscarPorUid (String uid);

}
