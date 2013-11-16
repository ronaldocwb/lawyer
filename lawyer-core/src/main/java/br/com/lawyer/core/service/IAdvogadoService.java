package br.com.lawyer.core.service;

import br.com.lawyer.core.base.IBaseService;
import br.com.lawyer.core.entity.Advogado;
import br.com.lawyer.core.repository.IAdvogadoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author Ronaldo
 * @since 23/10/2013
 */
public interface IAdvogadoService extends IBaseService<String, Advogado, IAdvogadoRepository> {

    Page<Advogado> buscarPorNomeLike(String query, PageRequest pageRequest);

    Advogado salvarAdvogado (Advogado parse);

    Advogado buscarAdvogadoPorPessoaUid (String pessoaUid);
}
