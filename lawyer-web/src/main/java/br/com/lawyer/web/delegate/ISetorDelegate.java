package br.com.lawyer.web.delegate;

import br.com.lawyer.web.vo.SetorVO;

import java.util.List;

/**
 * @author Deividi
 * @since 03/11/2013
 */
public interface ISetorDelegate {
    List<SetorVO> findAll ();

    SetorVO salvar (SetorVO setorVO);

}
