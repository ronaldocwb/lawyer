package br.com.lawyer.web.delegate.impl;

import br.com.lawyer.core.entity.common.Municipio;
import br.com.lawyer.core.service.IMunicipioService;
import br.com.lawyer.web.base.BaseDelegate;
import br.com.lawyer.web.delegate.IMunicipioDelegate;
import br.com.lawyer.web.vo.MunicipioVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author Deividi
 * @since 07/10/2013
 */
@Service
public class MunicipioDelegate extends BaseDelegate<Municipio, MunicipioVO> implements IMunicipioDelegate {

    @Autowired
    private IMunicipioService service;

    @Override
    public Page<MunicipioVO> buscaPorNome (String query, int page, int limit) {
        PageRequest pageRequest = new PageRequest(page, limit);
        Page<Municipio> municipios = service.buscaPorNomeContendo(query, pageRequest);
        return getVO(municipios, pageRequest);
    }
}
