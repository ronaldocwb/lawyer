package br.com.lawyer.web.authentication;

import br.com.lawyer.core.authentication.AuthenticationService;
import br.com.lawyer.core.authentication.LawyerAuthenticationToken;
import br.com.lawyer.core.entity.Permissao;
import br.com.lawyer.core.entity.PermissaoUsuario;
import br.com.lawyer.core.entity.Usuario;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Servico que realiza a autenticação do Spring security e cria o {@link br.com.lawyer.core.authentication.LawyerAuthenticationToken} para armazenamento na sessão - {SecurityContextHolder}.
 */
@Service("authenticationManager")
public class AuthenticationManagerImpl implements AuthenticationManager {

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * Realiza a autenticacao e atualiza o objeto Authetication
     * @param authentication
     */
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Usuario usuario = null;

        if (authentication.getName().equals(authentication.getCredentials())) {
            throw new BadCredentialsException("Usuário / Senha inválidos.");
        }

        if (authentication.getName() == null) {
            throw new BadCredentialsException("Usuário năo informado.");
        }

        if (authentication.getName().equals(authentication.getCredentials())) {
            throw new BadCredentialsException("Senha igual ao login.");
        }

        try {
            usuario = authenticationService.getUsuarioParaAutenticacao(authentication.getName());
        } catch (BusinessException e) {
            return authentication;
        }

        // Usuário não foi encontrado na base.
        if (usuario == null) {
            throw new BadCredentialsException("Usuário não encontrado na base de dados.");
        }

        if (!usuario.getSenha().equals(authentication.getCredentials().toString())) {
            throw new BadCredentialsException("Usuário / Senha inválidos.");
        }

        // Informações como IP e sessão ficam no WebAuthenticationDetails para monitoramento ou log.
        WebAuthenticationDetails webDetails =  (WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();

        String token = PasswordEncoder.generateRandomToken(authentication.getName());

        return new LawyerAuthenticationToken(authentication.getName(), authentication.getCredentials(), usuario, webDetails, getAuthorities(usuario.getPermissoes()), token);
    }

    /**
     * Obtem todas as permissőes deste usuário e insere como authorities
     * @param permissoes
     * @return List<GrantedAuthority>
     */
    public Collection<? extends GrantedAuthority> getAuthorities(List<PermissaoUsuario> permissoes) {

        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        for (PermissaoUsuario permissao : permissoes) {
            Permissao p = permissao.getPermissao();
            authList.add(new SimpleGrantedAuthority(p.toString()));
        }

        return authList;
    }
}