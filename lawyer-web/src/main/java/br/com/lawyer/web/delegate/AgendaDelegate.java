package br.com.lawyer.web.delegate;

import br.com.lawyer.core.entity.Agenda;

import java.util.List;

/**
 * @author Deividi
 * @since 20/12/2013
 */
public interface AgendaDelegate {

    Agenda salvar (Agenda agenda);

    void deletar (String uid);

    Agenda atualizar (Agenda agenda, String uid);

    List<Agenda> findAgendasPorUsuario ();

}
