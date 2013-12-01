package br.com.lawyer.web.api;

import br.com.lawyer.web.annotation.ApiController;
import br.com.lawyer.web.delegate.ClienteDelegate;
import br.com.lawyer.web.vo.ClienteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Deividi
 * @since 31/10/2013
 */
@ApiController
public class ClienteController {

    @Autowired
    private ClienteDelegate clienteDelegate;

    @RequestMapping (value = "/clientes", method = RequestMethod.GET)
    @ResponseBody
    public Page<ClienteVO> findAtividades (@RequestParam (value = "q", required = false) String q,
                                           @RequestParam (value = "page", defaultValue = "0", required = false) int page,
                                           @RequestParam (value = "limit", defaultValue = "25", required = false) int limit) {
        return clienteDelegate.findClientes(q, page, limit);
    }

}
