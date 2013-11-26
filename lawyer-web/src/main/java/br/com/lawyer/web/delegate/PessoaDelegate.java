package br.com.lawyer.web.delegate;

import br.com.lawyer.web.vo.PessoaVO;
import org.springframework.data.domain.Page;

/**
 * @author Deividi
 * @since 10/10/2013
 */
public interface PessoaDelegate {
    Page<PessoaVO> findPessoaPorPagina (String query, int page, int limit);

    PessoaVO salvar (PessoaVO pessoaVO);

    void deletar (String uid);

    PessoaVO atualizar (PessoaVO pessoaVO, String uid);

    PessoaVO buscarPorUid (String uid);
}
