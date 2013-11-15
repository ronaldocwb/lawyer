package br.com.lawyer.web.delegate.impl;

import br.com.lawyer.core.entity.Cliente;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.service.IClienteService;
import br.com.lawyer.web.base.BaseDelegate;
import br.com.lawyer.web.delegate.IClienteDelegate;
import br.com.lawyer.web.vo.ClienteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Deividi
 * @since 31/10/2013
 */
@Service
public class ClienteDelegate extends BaseDelegate<Cliente, ClienteVO> implements IClienteDelegate {

    @Autowired
    private IClienteService clienteService;

    @Override
    public ClienteVO findAdvocaciaUsuario () throws BusinessException {
        Cliente cliente = clienteService.findAdvocaciaUsuarioLogado();
        return getVO(cliente);
    }
}
