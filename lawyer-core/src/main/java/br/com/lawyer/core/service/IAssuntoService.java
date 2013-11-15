package br.com.lawyer.core.service;

import br.com.lawyer.core.base.IBaseService;
import br.com.lawyer.core.entity.Assunto;
import br.com.lawyer.core.repository.IAssuntoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author Deividi
 * @since 15/11/2013
 */
public interface IAssuntoService extends IBaseService<String, Assunto, IAssuntoRepository> {

    Page<Assunto> findAssuntoPorNomeOuPagina (String nome, PageRequest pageRequest);

}
