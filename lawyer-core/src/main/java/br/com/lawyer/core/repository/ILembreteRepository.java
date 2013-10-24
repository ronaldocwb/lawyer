package br.com.lawyer.core.repository;

import br.com.lawyer.core.base.IJPABaseRepository;
import br.com.lawyer.core.entity.Lembrete;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Deividi
 * @since 22/10/2013
 */
@Repository
public interface ILembreteRepository extends IJPABaseRepository<String, Lembrete> {

    List<Lembrete> findByUsuarioUid (String uid);

}
