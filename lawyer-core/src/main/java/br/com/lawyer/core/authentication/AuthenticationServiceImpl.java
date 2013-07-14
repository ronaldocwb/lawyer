package br.com.lawyer.core.authentication;

import br.com.lawyer.core.entity.Usuario;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Deividi
 * Serviço para autenticação de usuários na plataforma web.
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UsuarioRepository repository;

    /**
     * Recupera o usuário da base através de um e-mail informado.
     * @param email
     * @return {@link Usuario}
     */
    @Override
    public Usuario getUsuarioParaAutenticacao(String email) throws BusinessException {

        Usuario usuario = null;

        try {
            usuario = repository.findByEmail(email);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
        return usuario;
    }
}
