package br.com.lawyer.web.delegate;

import br.com.lawyer.web.vo.MunicipioVO;

import java.util.List;

/**
 * @author Deividi
 * @since 07/10/2013
 */
public interface IMunicipioDelegate {

    public List<MunicipioVO> buscaPorNome (String query);
}
