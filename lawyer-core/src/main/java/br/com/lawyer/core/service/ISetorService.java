package br.com.lawyer.core.service;

import br.com.lawyer.core.base.IBaseService;
import br.com.lawyer.core.entity.Setor;
import br.com.lawyer.core.repository.ISetorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author Deividi
 * @since 03/11/2013
 */
public interface ISetorService extends IBaseService<String, Setor, ISetorRepository> {
    Page<Setor> findByNome (String query, PageRequest pageRequest);
}
