package br.com.lawyer.core.service;

import br.com.lawyer.core.base.BaseService;
import br.com.lawyer.core.entity.Advogado;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.repository.AdvogadoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author Ronaldo
 * @since 23/10/2013
 */
public interface AdvogadoService extends BaseService<String, Advogado, AdvogadoRepository> {

    Page<Advogado> buscarPorNomeLike(String query, PageRequest pageRequest);

    Advogado salvarAdvogado (Advogado parse) throws BusinessException;

    Advogado buscarAdvogadoPorPessoaUid (String pessoaUid);

    void removerPorPessoaUid (String uid) throws BusinessException;
}
