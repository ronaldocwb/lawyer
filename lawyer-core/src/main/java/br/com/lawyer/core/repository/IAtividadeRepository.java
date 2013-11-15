package br.com.lawyer.core.repository;

import br.com.lawyer.core.base.IJPABaseRepository;
import br.com.lawyer.core.entity.Atividade;
import org.springframework.stereotype.Repository;

/**
 * @author Deividi
 * @since 11/11/2013
 */
@Repository
public interface IAtividadeRepository extends IJPABaseRepository<String, Atividade> {


}