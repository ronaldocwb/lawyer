package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.base.BaseServiceImpl;
import br.com.lawyer.core.entity.Agenda;
import br.com.lawyer.core.repository.AgendaRepository;
import br.com.lawyer.core.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Deividi
 * @since 20/12/2013
 */
@Service
public class AgendaServiceImpl extends BaseServiceImpl<String, Agenda, AgendaRepository> implements AgendaService {

    @Autowired
    public AgendaServiceImpl (AgendaRepository repository) {
        super(repository);
    }
}
