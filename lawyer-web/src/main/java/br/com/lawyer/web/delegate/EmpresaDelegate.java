package br.com.lawyer.web.delegate;

import br.com.lawyer.core.entity.Empresa;
import br.com.lawyer.core.exception.BusinessException;
import org.springframework.data.domain.Page;

/**
 * @author Deividi
 * @since 26/09/2013
 */
public interface EmpresaDelegate {

    Page<Empresa> findEmpresaPorPagina (String q, int page, int limit);

    Empresa salvar (Empresa empresaVO) throws BusinessException;

    void deletar (String uid) throws BusinessException;

    Empresa atualizar (Empresa empresaVO, String uid) throws BusinessException;

    Empresa buscarPorUid (String uid);
}
