package br.com.lawyer.core.repository.impl;

import br.com.lawyer.core.base.JPABaseRepository;
import br.com.lawyer.core.entity.Usuario;
import br.com.lawyer.core.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UsuarioRepository extends JPABaseRepository<String, Usuario> implements IUsuarioRepository {

    /**
     * Construtor básico para classe de acesso a dados (DAO)
     */
    @Autowired
    public UsuarioRepository (@Qualifier ("entityManager") EntityManager em) {
        super(Usuario.class, em);
    }

}
