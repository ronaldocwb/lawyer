package br.com.lawyer.core.service;

import br.com.lawyer.core.base.IBaseService;
import br.com.lawyer.core.entity.AreaAtuacao;
import br.com.lawyer.core.repository.IAreaAtuacaoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author Ronaldo
 * @since 26/09/2013
 */
public interface IAreaAtuacaoService extends IBaseService<String, AreaAtuacao, IAreaAtuacaoRepository> {

    Page<AreaAtuacao> buscarPorNomeLike (String query, PageRequest pageRequest);

}
