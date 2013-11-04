package br.com.lawyer.web.api;

import br.com.lawyer.web.annotation.ApiController;
import br.com.lawyer.web.delegate.ISetorDelegate;
import br.com.lawyer.web.vo.SetorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Deividi
 * @since 03/11/2013
 */
@ApiController
public class SetorController {

    @Autowired
    private ISetorDelegate setorDelegate;

    @RequestMapping (value = "/setores", method = RequestMethod.GET)
    public @ResponseBody List<SetorVO> findAll() {
        return setorDelegate.findAll();
    }

    @RequestMapping (value = "/setores", method = RequestMethod.POST)
    public @ResponseBody SetorVO salvar(@RequestBody SetorVO setorVO) {
        return setorDelegate.salvar(setorVO);
    }
}
