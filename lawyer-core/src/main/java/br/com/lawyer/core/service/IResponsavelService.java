package br.com.lawyer.core.service;

import br.com.lawyer.core.base.IBaseService;
import br.com.lawyer.core.entity.Responsavel;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.repository.IResponsavelRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Deividi
 * @since 22/10/2013
 */
public interface IResponsavelService extends IBaseService<String, Responsavel, IResponsavelRepository> {
    Page<Responsavel> buscarPorCampoLike (String query, String field, Pageable pageRequest) throws BusinessException;

}
