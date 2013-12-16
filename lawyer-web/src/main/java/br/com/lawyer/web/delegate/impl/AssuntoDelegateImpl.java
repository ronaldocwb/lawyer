package br.com.lawyer.web.delegate.impl;

import br.com.lawyer.core.entity.Assunto;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.service.AssuntoService;
import br.com.lawyer.web.delegate.AssuntoDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Deividi
 * @since 15/11/2013
 */
@Service
public class AssuntoDelegateImpl implements AssuntoDelegate {

    @Autowired
    private AssuntoService assuntoService;

    @Transactional
    @Override
    public Page<Assunto> findAssuntoPorNomeOuPagina (String nome, int page, int limit) {
        PageRequest pageRequest = new PageRequest(page, limit);
        Page<Assunto> assuntos = assuntoService.findAssuntoPorNomeOuPagina(nome, pageRequest);
        return assuntos;
    }

    @Override
    public Assunto salvar (Assunto assunto) {
        return assuntoService.save(assunto);
    }

    @Override
    public void deletar (String uid) {
        assuntoService.delete(uid);
    }

    @Override
    public Assunto update (Assunto assunto, String uid) {
        return assuntoService.save(assunto);
    }

    @Override
    public Assunto findOne (String uid) {
        Assunto assunto = assuntoService.findOne(uid);
        return assunto;
    }

    @Transactional
    @Override
    public Page<Assunto> findAssuntosPorClienteUid (String uid, int page, int limit) throws BusinessException {
        PageRequest pageRequest = new PageRequest(page, limit);
        Page<Assunto> assuntos = assuntoService.findAssuntosPorClienteUid(uid, pageRequest);
        return assuntos;
    }
}
