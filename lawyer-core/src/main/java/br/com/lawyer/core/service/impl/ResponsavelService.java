package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.base.BaseService;
import br.com.lawyer.core.entity.Responsavel;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.repository.IResponsavelRepository;
import br.com.lawyer.core.service.IResponsavelService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Deividi
 * @since 22/10/2013
 */
@Service
public class ResponsavelService extends BaseService<String, Responsavel, IResponsavelRepository> implements IResponsavelService {
    /**
     * Construtor
     *
     * @param repository - DAO que ser? utilizado referente a entidade manipulada
     */
    @Autowired
    public ResponsavelService (IResponsavelRepository repository) {
        super(repository);
    }

    @Override
    public Page<Responsavel> buscarPorCampoLike (String query, String field, Pageable pageRequest) throws BusinessException {
        Page<Responsavel> result = null;
        if (StringUtils.isBlank(field)) {
            throw new BusinessException("Busca deve informar o tipo: pessoa ou empresa");
        }
        if (StringUtils.isNotBlank(field)) {
        } else {
            result = getRepository().findAll(pageRequest);
        }
        return result;
    }

}
