package br.com.lawyer.web.delegate.impl;

import br.com.lawyer.core.entity.Setor;
import br.com.lawyer.core.service.SetorService;
import br.com.lawyer.web.delegate.SetorDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author Deividi
 * @since 03/11/2013
 */
@Service
public class SetorDelegateImpl implements SetorDelegate {

    @Autowired
    private SetorService setorService;

    @Override
    public Page<Setor> findAllByNome (String query, int page, int limit) {
        PageRequest pageRequest = new PageRequest(page, limit);
        Page<Setor> setors = setorService.findByNome(query, pageRequest);
        return setors;
    }

    @Override
    public Setor salvar (Setor setorVO) {
        Setor setor = setorService.save(setorVO);
        return setor;
    }
}
