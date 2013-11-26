package br.com.lawyer.web.delegate.impl;

import br.com.lawyer.core.entity.Setor;
import br.com.lawyer.core.service.SetorService;
import br.com.lawyer.web.base.BaseDelegate;
import br.com.lawyer.web.delegate.SetorDelegate;
import br.com.lawyer.web.vo.SetorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author Deividi
 * @since 03/11/2013
 */
@Service
public class SetorDelegateImpl extends BaseDelegate<Setor, SetorVO> implements SetorDelegate {

    @Autowired
    private SetorService setorService;

    @Override
    public Page<SetorVO> findAllByNome (String query, int page, int limit) {
        PageRequest pageRequest = new PageRequest(page, limit);
        Page<Setor> setors = setorService.findByNome(query, pageRequest);
        return getVO(setors, pageRequest);
    }

    @Override
    public SetorVO salvar (SetorVO setorVO) {
        Setor setor = setorService.save(setorVO.parse());
        return getVO(setor);
    }
}
