package br.com.lawyer.web.delegate.impl;

import br.com.lawyer.core.entity.Cliente;
import br.com.lawyer.core.service.ClienteService;
import br.com.lawyer.web.delegate.ClienteDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author Deividi
 * @since 22/11/2013
 */
@Service
public class ClienteDelegateImpl implements ClienteDelegate {

    @Autowired
    private ClienteService clienteService;

    @Override
    public Page<Cliente> findClientes (String query, int page, int limit) {
        PageRequest pageRequest = new PageRequest(page, limit);
        Page<Cliente> clientes = clienteService.findClientes(query, pageRequest);
        return clientes;
    }

    @Override
    public Cliente salvar (Cliente clienteVO) {
        Cliente cliente = clienteService.save(clienteVO);
        return cliente;
    }

    @Override
    public void deletar (String uid) {
        clienteService.delete(uid);
    }

    @Override
    public Cliente atualizar (Cliente clienteVO, String uid) {
        Cliente cliente = clienteService.save(clienteVO);
        return cliente;
    }

    @Override
    public Cliente buscarPorUid (String uid) {
        Cliente cliente = clienteService.findOne(uid);
        return cliente;
    }
}
