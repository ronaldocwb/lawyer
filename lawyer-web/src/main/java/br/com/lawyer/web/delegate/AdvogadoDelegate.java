package br.com.lawyer.web.delegate;

import br.com.lawyer.web.vo.AdvogadoVO;
import org.springframework.data.domain.Page;

/**
 * @author Ronaldo
 * @since 10/10/2013
 */
public interface AdvogadoDelegate {
    Page<AdvogadoVO> findAdvogadoPorPagina (String query, int page, int limit);

    AdvogadoVO salvar (AdvogadoVO advogadoVO);

    void deletar (String uid);

    AdvogadoVO atualizar (AdvogadoVO advogadoVO, String uid);

    AdvogadoVO buscarPorUid (String uid);

    AdvogadoVO buscarAdvogadoPorPessoaUid (String pessoaUid);
}
