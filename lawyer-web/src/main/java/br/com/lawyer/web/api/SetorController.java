package br.com.lawyer.web.api;

import br.com.lawyer.web.annotation.ApiController;
import br.com.lawyer.web.delegate.ISetorDelegate;
import br.com.lawyer.web.vo.SetorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author Deividi
 * @since 03/11/2013
 */
@ApiController
public class SetorController {

    @Autowired
    private ISetorDelegate setorDelegate;

    @RequestMapping (value = "/setores", method = RequestMethod.GET)
    @ResponseBody
    public Page<SetorVO> findAll (
            @RequestParam (value = "q", required = false) String query,
            @RequestParam (value = "page", defaultValue = "0", required = false) int page,
            @RequestParam (value = "limit", defaultValue = "25", required = false) int limit) {
        return setorDelegate.findAllByNome(query, page, limit);
    }

    @RequestMapping (value = "/setores", method = RequestMethod.POST)
    @ResponseBody
    public SetorVO salvar(@RequestBody SetorVO setorVO) {
        return setorDelegate.salvar(setorVO);
    }
}
