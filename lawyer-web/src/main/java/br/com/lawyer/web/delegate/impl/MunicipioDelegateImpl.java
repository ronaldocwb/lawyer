package br.com.lawyer.web.delegate.impl;

import br.com.lawyer.core.entity.common.Municipio;
import br.com.lawyer.core.service.MunicipioService;
import br.com.lawyer.web.delegate.MunicipioDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author Deividi
 * @since 07/10/2013
 */
@Service
public class MunicipioDelegateImpl implements MunicipioDelegate {

    @Autowired
    private MunicipioService service;

    @Override
    public Page<Municipio> buscaPorNome (String query, int page, int limit) {
        PageRequest pageRequest = new PageRequest(page, limit);
        Page<Municipio> municipios = service.buscaPorNomeContendo(query, pageRequest);
        return municipios;
    }
}
