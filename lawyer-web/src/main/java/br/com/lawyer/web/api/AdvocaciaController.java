package br.com.lawyer.web.api;

import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.annotation.ApiController;
import br.com.lawyer.web.delegate.IAdvocaciaDelegate;
import br.com.lawyer.web.vo.AdvocaciaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Deividi
 * @since 31/10/2013
 */
@ApiController
public class AdvocaciaController {

    @Autowired
    private IAdvocaciaDelegate clienteDelegate;

    @RequestMapping(value = "/advocacia", method = RequestMethod.GET)
    public @ResponseBody
    AdvocaciaVO getAdvocaciaUsuario() throws BusinessException {
        return clienteDelegate.findAdvocaciaUsuario();
    }

}
