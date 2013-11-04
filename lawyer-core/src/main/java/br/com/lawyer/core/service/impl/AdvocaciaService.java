package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.base.BaseService;
import br.com.lawyer.core.entity.Advocacia;
import br.com.lawyer.core.entity.Usuario;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.repository.IAdvocaciaRepository;
import br.com.lawyer.core.service.IAdvocaciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Deividi
 * @since 31/10/2013
 */
@Service
public class AdvocaciaService extends BaseService<String, Advocacia, IAdvocaciaRepository> implements IAdvocaciaService {

    @Autowired
    public AdvocaciaService (IAdvocaciaRepository repository) {
        super(repository);
    }

    @Override
    public Advocacia findAdvocaciaUsuarioLogado () throws BusinessException {
        Usuario usuario = getUsuarioLogado();
        return getRepository().findOne(usuario.getAdvocacia().getUid());
    }
}
