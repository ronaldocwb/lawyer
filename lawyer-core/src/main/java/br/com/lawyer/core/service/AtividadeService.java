package br.com.lawyer.core.service;

import br.com.lawyer.core.base.BaseService;
import br.com.lawyer.core.entity.Atividade;
import br.com.lawyer.core.repository.AtividadeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author Deividi
 * @since 11/11/2013
 */
public interface AtividadeService extends BaseService<String, Atividade, AtividadeRepository> {

    Page<Atividade> buscarPorNomeLike (String query, PageRequest pageRequest);
}
