package br.com.lawyer.web.delegate.impl;

import br.com.lawyer.core.entity.common.Municipio;
import br.com.lawyer.core.service.IMunicipioService;
import br.com.lawyer.web.base.BaseDelegate;
import br.com.lawyer.web.delegate.IMunicipioDelegate;
import br.com.lawyer.web.vo.MunicipioVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Deividi
 * @since 07/10/2013
 */
@Service
public class MunicipioDelegate extends BaseDelegate<Municipio, MunicipioVO> implements IMunicipioDelegate {

    @Autowired
    private IMunicipioService service;

    @Override
    public List<MunicipioVO> buscaPorNome (String query) {
        List<Municipio> municipios = service.buscaPorNomeContendo(query);
        return getVO(municipios);
    }
}
