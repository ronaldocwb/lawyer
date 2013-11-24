package br.com.lawyer.web.delegate.impl;

import br.com.lawyer.core.entity.Cliente;
import br.com.lawyer.core.service.IClienteService;
import br.com.lawyer.web.base.BaseDelegate;
import br.com.lawyer.web.delegate.IClienteDelegate;
import br.com.lawyer.web.vo.ClienteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Deividi
 * @since 22/11/2013
 */
@Service
public class ClienteDelegate extends BaseDelegate<Cliente, ClienteVO> implements IClienteDelegate {

    @Autowired
    private IClienteService clienteService;

    @Override
    public Page<ClienteVO> findClientes (String query, int page, int limit) {
        PageRequest pageRequest = new PageRequest(page, limit);
        Page<Cliente> clientes = clienteService.findClientes(query, pageRequest);
        return getVO(clientes, pageRequest);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ClienteVO salvar (ClienteVO clienteVO) {
        Cliente cliente = clienteService.salvar(clienteVO.parse());
        return getVO(cliente);
    }

    @Override
    public void deletar (String uid) {
        clienteService.delete(uid);
    }

    @Override
    public ClienteVO atualizar (ClienteVO clienteVO, String uid) {
        Cliente cliente = clienteService.saveAndFlush(clienteVO.parse());
        return getVO(cliente);
    }

    @Override
    public ClienteVO buscarPorUid (String uid) {
        Cliente cliente = clienteService.findOne(uid);
        return getVO(cliente);
    }
}
