package br.com.lawyer.core.service;

import br.com.lawyer.core.base.BaseService;
import br.com.lawyer.core.entity.Setor;
import br.com.lawyer.core.repository.SetorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author Deividi
 * @since 03/11/2013
 */
public interface SetorService extends BaseService<String, Setor, SetorRepository> {
    Page<Setor> findByNome (String query, PageRequest pageRequest);
}
