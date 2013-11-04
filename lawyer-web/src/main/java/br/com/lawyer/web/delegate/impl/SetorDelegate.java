package br.com.lawyer.web.delegate.impl;

import br.com.lawyer.core.entity.Setor;
import br.com.lawyer.core.service.ISetorService;
import br.com.lawyer.web.base.BaseDelegate;
import br.com.lawyer.web.delegate.ISetorDelegate;
import br.com.lawyer.web.vo.SetorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Deividi
 * @since 03/11/2013
 */
@Service
public class SetorDelegate extends BaseDelegate<Setor, SetorVO> implements ISetorDelegate {

    @Autowired
    private ISetorService setorService;

    @Override
    public List<SetorVO> findAll () {
        List<Setor> setors = setorService.findAll();
        return getVO(setors);
    }

    @Override
    public SetorVO salvar (SetorVO setorVO) {
        Setor setor = setorService.save(setorVO.parse());
        return getVO(setor);
    }
}
