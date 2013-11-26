package br.com.lawyer.core.service;

import br.com.lawyer.core.base.BaseService;
import br.com.lawyer.core.entity.Assunto;
import br.com.lawyer.core.repository.AssuntoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author Deividi
 * @since 15/11/2013
 */
public interface AssuntoService extends BaseService<String, Assunto, AssuntoRepository> {

    Page<Assunto> findAssuntoPorNomeOuPagina (String nome, PageRequest pageRequest);

}
