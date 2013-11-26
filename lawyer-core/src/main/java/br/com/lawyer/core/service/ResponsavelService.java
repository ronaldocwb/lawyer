package br.com.lawyer.core.service;

import br.com.lawyer.core.base.BaseService;
import br.com.lawyer.core.entity.Responsavel;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.repository.ResponsavelRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Deividi
 * @since 22/10/2013
 */
public interface ResponsavelService extends BaseService<String, Responsavel, ResponsavelRepository> {
    Page<Responsavel> buscarPorCampoLike (String query, Pageable pageRequest) throws BusinessException;

}
