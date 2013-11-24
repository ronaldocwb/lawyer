package br.com.lawyer.core.repository;

import br.com.lawyer.core.base.IJPABaseRepository;
import br.com.lawyer.core.entity.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends IJPABaseRepository<String, Usuario> {
    Usuario findByEmail(String email);

}
