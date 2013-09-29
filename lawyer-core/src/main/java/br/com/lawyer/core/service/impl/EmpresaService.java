package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.base.BaseService;
import br.com.lawyer.core.entity.Empresa;
import br.com.lawyer.core.repository.IEmpresaRepository;
import br.com.lawyer.core.service.IEmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Deividi
 * @since 26/09/2013
 */
@Service
public class EmpresaService extends BaseService<String, Empresa, IEmpresaRepository> implements IEmpresaService {
    /**
     * Construtor
     *
     * @param repository - DAO que será utilizado referente a entidade manipulada
     */
    @Autowired
    public EmpresaService (IEmpresaRepository repository) {
        super(repository);
    }

}
