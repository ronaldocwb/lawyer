package br.com.lawyer.core.service;

import br.com.lawyer.core.base.BaseService;
import br.com.lawyer.core.entity.AreaAtuacao;
import br.com.lawyer.core.repository.AreaAtuacaoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author Ronaldo
 * @since 26/09/2013
 */
public interface AreaAtuacaoService extends BaseService<String, AreaAtuacao, AreaAtuacaoRepository> {

    Page<AreaAtuacao> buscarPorNomeLike (String query, PageRequest pageRequest);

}
