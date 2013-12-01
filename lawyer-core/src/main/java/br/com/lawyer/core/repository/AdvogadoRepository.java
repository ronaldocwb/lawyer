package br.com.lawyer.core.repository;

import br.com.lawyer.core.base.JPABaseRepository;
import br.com.lawyer.core.entity.Advogado;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Ronaldo
 * @since 23/10/2013
 */
@Repository
public interface AdvogadoRepository extends JPABaseRepository<String, Advogado> {

    @Modifying
    @Query("delete from Advogado o where o.pessoa.uid = :uid")
    void deletarPorPessoaUid (@Param ("uid") String uid);
}