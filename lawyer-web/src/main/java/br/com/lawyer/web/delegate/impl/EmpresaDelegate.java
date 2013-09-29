package br.com.lawyer.web.delegate.impl;

import br.com.lawyer.core.entity.Empresa;
import br.com.lawyer.core.service.IEmpresaService;
import br.com.lawyer.web.base.BaseDelegate;
import br.com.lawyer.web.delegate.IEmpresaDelegate;
import br.com.lawyer.web.vo.EmpresaVO;
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
public class EmpresaDelegate extends BaseDelegate<Empresa, EmpresaVO> implements IEmpresaDelegate {

    @Autowired
    private IEmpresaService empresaService;

    @Transactional
    @Override
    public Page findEmpresaPorPagina (PageRequest pageRequest) {
        Page<Empresa> empresas = empresaService.findAll(pageRequest);
        return getVO(empresas, pageRequest);
    }

    @Override
    public EmpresaVO salvar (EmpresaVO empresaVO) {
        Empresa empresa = empresaService.save(empresaVO.parse());
        return getVO(empresa);
    }

    @Override
    public void deletar (String uid) {
        empresaService.delete(uid);
    }

    @Override
    public EmpresaVO atualizar (EmpresaVO empresaVO, String uid) {
        Empresa empresa = empresaService.save(empresaVO.parse());
        return getVO(empresa);
    }

    @Override
    public EmpresaVO buscarPorUid (String uid) {
        Empresa empresa = empresaService.findOne(uid);
        return getVO(empresa);
    }
}
