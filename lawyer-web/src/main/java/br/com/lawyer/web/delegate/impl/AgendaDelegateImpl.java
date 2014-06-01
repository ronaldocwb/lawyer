package br.com.lawyer.web.delegate.impl;

import br.com.lawyer.core.entity.Agenda;
import br.com.lawyer.core.service.AgendaService;
import br.com.lawyer.web.delegate.AgendaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Deividi
 * @since 20/12/2013
 */
@Service
public class AgendaDelegateImpl implements AgendaDelegate {

    @Autowired
    private AgendaService agendaService;

    @Override
    public Agenda salvar (Agenda agenda) {
        return null;
    }

    @Override
    public void deletar (String uid) {

    }

    @Override
    public Agenda atualizar (Agenda agenda, String uid) {
        return null;
    }

    @Override
    public List<Agenda> findAgendasPorUsuario () {
        return null;
    }
}
