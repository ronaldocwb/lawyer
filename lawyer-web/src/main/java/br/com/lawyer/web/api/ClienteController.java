package br.com.lawyer.web.api;

import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.annotation.ApiController;
import br.com.lawyer.web.delegate.IClienteDelegate;
import br.com.lawyer.web.vo.ClienteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Deividi
 * @since 31/10/2013
 */
@ApiController
public class ClienteController {

    @Autowired
    private IClienteDelegate clienteDelegate;

    @RequestMapping(value = "/cliente", method = RequestMethod.GET)
    public @ResponseBody
    ClienteVO getAdvocaciaUsuario() throws BusinessException {
        return clienteDelegate.findAdvocaciaUsuario();
    }

}
