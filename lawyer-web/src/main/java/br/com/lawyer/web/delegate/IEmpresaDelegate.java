package br.com.lawyer.web.delegate;

import br.com.lawyer.web.vo.EmpresaVO;
import org.springframework.data.domain.Page;

/**
 * @author Deividi
 * @since 26/09/2013
 */
public interface IEmpresaDelegate  {
    Page<EmpresaVO> findEmpresaPorPagina (String q, int page, int limit);

    EmpresaVO salvar (EmpresaVO empresaVO);

    void deletar (String uid);

    EmpresaVO atualizar (EmpresaVO empresaVO, String uid);

    EmpresaVO buscarPorUid (String uid);
}
