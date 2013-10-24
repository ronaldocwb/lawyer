package br.com.lawyer.web.delegate;

import org.springframework.data.domain.Page;

import br.com.lawyer.web.vo.AdvogadoVO;

/**
 * @author Ronaldo
 * @since 10/10/2013
 */
public interface IAdvogadoDelegate {
    Page<AdvogadoVO> findAdvogadoPorPagina (String query, int page, int limit);

    AdvogadoVO salvar (AdvogadoVO advogadoVO);

    void deletar (String uid);

    AdvogadoVO atualizar (AdvogadoVO advogadoVO, String uid);

    AdvogadoVO buscarPorUid (String uid);
}
