package br.com.lawyer.web.delegate;

import br.com.lawyer.web.vo.AreaAtuacaoVO;
import org.springframework.data.domain.Page;

/**
 * @author Ronaldo
 * @since 26/09/2013
 */
public interface IAreaAtuacaoDelegate  {
    Page<AreaAtuacaoVO> findAreaAtuacaoPorPagina (String q, int page, int limit);

    AreaAtuacaoVO salvar (AreaAtuacaoVO areaAtuacaoVO);

    void deletar (String uid);

    AreaAtuacaoVO atualizar (AreaAtuacaoVO areaAtuacaoVO, String uid);

    AreaAtuacaoVO buscarPorUid (String uid);
}
