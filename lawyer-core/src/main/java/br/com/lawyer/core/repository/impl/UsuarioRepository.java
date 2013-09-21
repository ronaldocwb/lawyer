package br.com.lawyer.core.repository.impl;

import br.com.lawyer.core.base.JPABaseRepository;
import br.com.lawyer.core.entity.Usuario;
import br.com.lawyer.core.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

/**
 * @author Deividi Cavarzan
 * @since 20/09/2013
 *
 * Essa implementação não é necessária até que algum método seja implementado.
 * Deixada aqui como exemplo.
 */
@Service
public class UsuarioRepository extends JPABaseRepository<String, Usuario> implements IUsuarioRepository {

    /**
     * Construtor básico para classe de acesso a dados (DAO)
     */
    @Autowired
    public UsuarioRepository (@Qualifier ("entityManager") EntityManager em) {
        super(Usuario.class, em);
    }


}
