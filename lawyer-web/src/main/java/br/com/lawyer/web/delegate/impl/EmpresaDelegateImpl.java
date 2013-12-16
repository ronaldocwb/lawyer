package br.com.lawyer.web.delegate.impl;

import br.com.lawyer.core.entity.Empresa;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.service.EmpresaService;
import br.com.lawyer.web.delegate.EmpresaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Deividi
 * @since 26/09/2013
 */
@Service
public class EmpresaDelegateImpl implements EmpresaDelegate {

    @Autowired
    private EmpresaService empresaService;

    @Transactional
    @Override
    public Page<Empresa> findEmpresaPorPagina (String query, int page, int limit) {
        PageRequest pageRequest = new PageRequest(page, limit);
        Page<Empresa> empresas = empresaService.buscarPorRazaoSocialOuNomeFantasiaLike(query, pageRequest);
        return empresas;
    }

    @Override
    @Transactional
    public Empresa salvar (Empresa empresaVO) throws BusinessException {
        Empresa empresa = empresaService.salvar(empresaVO);
        return empresa;
    }

    @Override
    @Transactional
    public void deletar (String uid) throws BusinessException {
        empresaService.deletarEmpresa(uid);
    }

    @Override
    @Transactional
    public Empresa atualizar (Empresa empresaVO, String uid) throws BusinessException {
        Empresa empresa = empresaService.atualizar(empresaVO);
        return empresa;
    }

    @Override
    public Empresa buscarPorUid (String uid) {
        Empresa empresa = empresaService.findOne(uid);
        return empresa;
    }
}
