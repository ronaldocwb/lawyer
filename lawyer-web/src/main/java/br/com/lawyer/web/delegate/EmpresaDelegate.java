package br.com.lawyer.web.delegate;

import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.vo.EmpresaVO;
import org.springframework.data.domain.Page;

/**
 * @author Deividi
 * @since 26/09/2013
 */
public interface EmpresaDelegate {
    Page<EmpresaVO> findEmpresaPorPagina (String q, int page, int limit);

    EmpresaVO salvar (EmpresaVO empresaVO) throws BusinessException;

    void deletar (String uid) throws BusinessException;

    EmpresaVO atualizar (EmpresaVO empresaVO, String uid) throws BusinessException;

    EmpresaVO buscarPorUid (String uid);
}
