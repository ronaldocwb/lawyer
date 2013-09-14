package br.com.lawyer.core.bo.impl;

import br.com.lawyer.core.authentication.LawyerAuthenticationToken;
import br.com.lawyer.core.base.BaseBO;
import br.com.lawyer.core.bo.IUsuarioBO;
import br.com.lawyer.core.entity.Usuario;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.repository.IUsuarioRepository;
import br.com.lawyer.core.util.LawyerStringUtils;
import br.com.lawyer.core.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author Deividi Cavarzan
 */
@Service
public class UsuarioBO extends BaseBO<String, Usuario, IUsuarioRepository> implements IUsuarioBO {

    /**
     * Construtor
     *
     * @param dao - DAO que será utilizado referente a entidade manipulada
     */
    @Autowired
    public UsuarioBO (IUsuarioRepository dao) {
        super(dao);
    }

    /**
     * Realiza a autenticação do usuario com base na entidade informada e no manager que implementa a regra de autenticação.
     * Qualquer exceção de negocio na autenticação lança uma {@link BadCredentialsException} em runtime.
     * @param user
     * @param manager
     * @return
     * @throws BusinessException
     * @throws BadCredentialsException
     */
    @Override
    public Usuario authenticate (Usuario user, AuthenticationManager manager) throws BusinessException {

        if (LawyerStringUtils.containStringBlank(user.getEmail(), user.getSenha())) {
            throw new BadCredentialsException("Usuario / Senha nao informado.");
        }

        String password = PasswordEncoder.encodePassword(user.getSenha(), user.getEmail());

        Usuario usuario = getDAO().findByProperty("email", user.getEmail());

        LawyerAuthenticationToken auth = new LawyerAuthenticationToken(user.getEmail(), password, usuario);
        auth = (LawyerAuthenticationToken) manager.authenticate(auth);

        if (auth.isAuthenticated()) {

            // Insere nosso token na sessão para ficar disponpivel para consulta.
            SecurityContextHolder.getContext().setAuthentication(auth);
            usuario.setHashAutenticacao(auth.getToken());

        } else {
            throw new BadCredentialsException("Usuário não foi autenticado.");
        }

        return usuario;
    }


}
