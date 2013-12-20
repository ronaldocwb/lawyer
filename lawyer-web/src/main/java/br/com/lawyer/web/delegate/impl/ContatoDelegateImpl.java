package br.com.lawyer.web.delegate.impl;

import br.com.lawyer.core.entity.Contato;
import br.com.lawyer.core.service.ContatoService;
import br.com.lawyer.web.delegate.ContatoDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Deividi
 * @since 22/11/2013
 */
@Service
public class ContatoDelegateImpl implements ContatoDelegate {

    @Autowired
    private ContatoService contatoService;

    @Override
    public Page<Contato> findContatos (String query, String tipo, int page, int limit) {
        PageRequest pageRequest = new PageRequest(page, limit);
        Page<Contato> clientes = contatoService.findContatos(query, tipo, pageRequest);
        return clientes;
    }

    @Transactional
    @Override
    public Contato salvar (Contato entity) {
        Contato contato = contatoService.save(entity);
        return contato;
    }

    @Transactional
    @Override
    public void deletar (String uid) {
        contatoService.delete(uid);
    }

    @Transactional
    @Override
    public Contato atualizar (Contato entity, String uid) {
        Contato contato = contatoService.save(entity);
        return contato;
    }


    @Transactional(readOnly = true)
    @Override
    public Contato buscarPorUid (String uid) {
        Contato contato = contatoService.findOne(uid);
        return contato;
    }

    @Override
    public Page<Contato> findContatosEmpresas (String q, int page, int limit) {
        PageRequest pageRequest = new PageRequest(page, limit);
        Page<Contato> clientes = contatoService.findContatosEmpresas(q, pageRequest);
        return clientes;
    }

    @Override
    public Page<Contato> findContatosPessoas (String q, int page, int limit) {
        PageRequest pageRequest = new PageRequest(page, limit);
        Page<Contato> clientes = contatoService.findContatosPessoas(q, pageRequest);
        return clientes;
    }

    @Override
    public Page<Contato> findContatosUsuarios (String q, int page, int limit) {
        PageRequest pageRequest = new PageRequest(page, limit);
        Page<Contato> usuarios = contatoService.findContatosUsuarios(q, pageRequest);
        return usuarios;
    }
}
