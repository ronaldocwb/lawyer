package br.com.lawyer.core.service;

import br.com.lawyer.core.base.IBaseService;
import br.com.lawyer.core.entity.Atividade;
import br.com.lawyer.core.repository.IAtividadeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author Deividi
 * @since 11/11/2013
 */
public interface IAtividadeService extends IBaseService<String, Atividade, IAtividadeRepository> {

    Page<Atividade> buscarPorNomeLike (String query, PageRequest pageRequest);
}
