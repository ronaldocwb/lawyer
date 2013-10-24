package br.com.lawyer.core.repository.impl;

import br.com.lawyer.core.base.JPABaseRepository;
import br.com.lawyer.core.entity.Usuario;
import br.com.lawyer.core.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * @author Deividi Cavarzan
 * @since 20/09/2013
 *        <p/>
 *        Essa implementa��o n�o � necess�ria at� que algum m�todo seja implementado.
 *        Deixada aqui como exemplo.
 */
@Service
public class UsuarioRepository extends JPABaseRepository<String, Usuario> implements IUsuarioRepository {

    /**
     * Construtor b�sico para classe de acesso a dados (DAO)
     */
    @Autowired
    public UsuarioRepository (@Qualifier ("entityManager") EntityManager em) {
        super(Usuario.class, em);
    }


    @Override
    public Usuario findByEmail (String email) {
        Query q = getEntityManager().createQuery("SELECT o FROM Usuario o WHERE o.email = :EMAIL");
        q.setParameter("EMAIL", email);

        return (Usuario) q.getSingleResult();
    }
}
