package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.authentication.LawyerAuthenticationToken;
import br.com.lawyer.core.base.BaseServiceImpl;
import br.com.lawyer.core.entity.Usuario;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.repository.UsuarioRepository;
import br.com.lawyer.core.service.UsuarioService;
import br.com.lawyer.core.util.PasswordEncoder;
import br.com.lawyer.core.util.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author Deividi Cavarzan
 */
@SuppressWarnings ("ALL")
@Service
public class UsuarioServiceImpl extends BaseServiceImpl<String, Usuario, UsuarioRepository> implements UsuarioService {

    private static final Logger logger = Logger.getLogger(UsuarioService.class);
    /**
     * Construtor
     *
     * @param repository - DAO que será utilizado referente a entidade Usuario
     */
    @Autowired
    public UsuarioServiceImpl (UsuarioRepository repository) {
        super(repository);
    }

    /**
     * Realiza a autenticação do usuario com base na entidade informada e no manager que implementa a regra de autenticação.
     * Qualquer exceção de negocio na autenticação lança uma {@link BadCredentialsException} em runtime.
     *
     * @param user
     * @param manager
     * @return
     * @throws BusinessException
     * @throws BadCredentialsException
     */
    @Override
    public Usuario authenticate (Usuario user, AuthenticationManager manager) {

        logger.info("realizando autenticacao do usuario " + user.getEmail());

        if (StringUtils.containStringBlank(user.getEmail(), user.getSenha())) {
            throw new IllegalArgumentException("Usuario / Senha nao informado.");
        }

        String password = PasswordEncoder.encodePassword(user.getSenha(), user.getEmail());

        Usuario usuario = null;
        try {
            usuario = getRepository().findByEmail(user.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }

        LawyerAuthenticationToken auth = new LawyerAuthenticationToken(user.getEmail(), password, usuario);
        auth = (LawyerAuthenticationToken) manager.authenticate(auth);

        if (auth.isAuthenticated()) {

            // Insere nosso token na sess�o para ficar disponpivel para consulta.
            SecurityContextHolder.getContext().setAuthentication(auth);

        } else {
            throw new BadCredentialsException("Usuário não foi autenticado.");
        }

        logger.info("Usuario autenticado: " + user.getEmail());
        return usuario;
    }

    @Override
    public Usuario atualizarUsuario (Usuario usuario, String uid) throws BusinessException {

        if (StringUtils.isBlank(uid) || StringUtils.isBlank(usuario.getUid())) {
            throw new IllegalArgumentException("Usuário deve ser informado!");
        }

        if (!getRepository().exists(uid)) {
            throw new BusinessException("Entidade informada não existe.");
        }

        if (uid.equals(usuario.getUid())) {
            return getRepository().save(usuario);
        }

        return null;
    }

    @Override
    public Usuario atualizarSenhaUsuario (Usuario usuario, String token, String novaSenha) throws AuthenticationException, BusinessException {
        logger.info(String.format("Alterando a senha do usuario de UID %s", usuario.getUid()));

        LawyerAuthenticationToken auth = getCredenciais();

        if (StringUtils.isBlank(novaSenha)) {
            throw new IllegalArgumentException("Senha não pode ser em branco.");
        }

        if (usuario == null) {
            throw new IllegalArgumentException("Usuário deve ser informado.");
        }

        if (!usuario.getEmail().equals(auth.getUsuario().getEmail()) || !token.equals(auth.getToken())) {
            throw new BusinessException("Credenciais inválidas");
        }
        String oldPassword = PasswordEncoder.encodePassword(usuario.getSenha(), auth.getUsuario().getEmail());

        if (!oldPassword.equals(auth.getUsuario().getSenha())) {
            throw new BusinessException("Senha atual incorreta.");
        }

        String password = PasswordEncoder.encodePassword(novaSenha, auth.getUsuario().getEmail());

        if (password.equals(auth.getUsuario().getSenha())) {
            throw new BusinessException("Senha é igual a anterior.");
        }

        auth.getUsuario().setSenha(password);
        getRepository().save(auth.getUsuario());

        logger.info(String.format("Senha do usuario de UID %s foi alterada.", usuario.getUid()));

        return auth.getUsuario();
    }


}
