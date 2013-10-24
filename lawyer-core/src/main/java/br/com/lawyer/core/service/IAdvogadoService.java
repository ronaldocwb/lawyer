package br.com.lawyer.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.lawyer.core.base.IBaseService;
import br.com.lawyer.core.entity.Advogado;
import br.com.lawyer.core.repository.IAdvogadoRepository;

/**
 * @author Ronaldo
 * @since 23/10/2013
 */
public interface IAdvogadoService extends IBaseService<String, Advogado, IAdvogadoRepository> {

    Page<Advogado> buscarPorNomeLike(String query, PageRequest pageRequest);

}
