package br.com.lawyer.core.repository;

import br.com.lawyer.core.base.IJPABaseRepository;
import br.com.lawyer.core.entity.Lembrete;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Deividi
 * @since 22/10/2013
 */
@Repository
public interface ILembreteRepository extends IJPABaseRepository<String, Lembrete> {

    List<Lembrete> findByUsuarioUid (String uid);

    @Transactional
    @Modifying
    @Query ("DELETE FROM Lembrete o WHERE o.usuario.uid = :uid")
    void deletarTodosLembretesUsuario (@Param("uid") String uid);
}
