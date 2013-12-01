package br.com.lawyer.web.delegate;

import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.vo.PessoaVO;
import org.springframework.data.domain.Page;

/**
 * @author Deividi
 * @since 10/10/2013
 */
public interface PessoaDelegate {
    Page<PessoaVO> findPessoaPorPagina (String query, int page, int limit);

    PessoaVO salvar (PessoaVO pessoaVO) throws BusinessException;

    void deletar (String uid) throws BusinessException;

    PessoaVO atualizar (PessoaVO pessoaVO, String uid) throws BusinessException;

    PessoaVO buscarPorUid (String uid);
}
