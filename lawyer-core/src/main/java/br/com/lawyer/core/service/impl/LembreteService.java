package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.authentication.LawyerAuthenticationToken;
import br.com.lawyer.core.base.BaseService;
import br.com.lawyer.core.entity.Lembrete;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.repository.ILembreteRepository;
import br.com.lawyer.core.service.ILembreteService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Deividi
 * @since 22/10/2013
 */
@Service
public class LembreteService extends BaseService<String, Lembrete, ILembreteRepository> implements ILembreteService {
    /**
     * Construtor
     *
     * @param repository - DAO que ser? utilizado referente a entidade manipulada
     */
    @Autowired
    public LembreteService (ILembreteRepository repository) {
        super(repository);
    }

    @Override
    public List<Lembrete> findAllByCurrentUser () throws BusinessException {
        LawyerAuthenticationToken token = (LawyerAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        if (token.getUsuario() == null || StringUtils.isBlank(token.getUsuario().getUid())) {
            throw new BusinessException("Usuario nao encontrado na autenticação.");
        }
        return getRepository().findByUsuarioUid(token.getUsuario().getUid());
    }
}
