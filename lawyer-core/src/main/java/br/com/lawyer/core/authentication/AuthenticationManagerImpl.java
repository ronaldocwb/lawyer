package br.com.lawyer.core.authentication;

import br.com.lawyer.core.entity.Usuario;
import br.com.lawyer.core.entity.enumerated.Permissao;
import br.com.lawyer.core.util.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Servico que realiza a autenticacao do Spring security e cria o {@link br.com.lawyer.core.authentication.LawyerAuthenticationToken} para armazenamento na sessao - {SecurityContextHolder}.
 */
@Service ("authenticationManager")
public class AuthenticationManagerImpl implements AuthenticationManager {

    /**
     * Realiza a autenticacao e atualiza o objeto Authetication
     *
     * @param authentication
     */
    public Authentication authenticate (Authentication authentication) throws AuthenticationException {

        LawyerAuthenticationToken lawyerAuthenticationToken = (LawyerAuthenticationToken) authentication;
        Usuario usuario = lawyerAuthenticationToken.getUsuario();

        if (usuario == null || authentication.getCredentials() == null) {
            throw new BadCredentialsException("Usuário não encontrado.");
        }

        if (authentication.getName().equals(authentication.getCredentials())) {
            throw new BadCredentialsException("Usuário / Senha inválidos.");
        }

        if (authentication.getName() == null) {
            throw new BadCredentialsException("Usuário não informado.");
        }

        if (authentication.getName().equals(authentication.getCredentials())) {
            throw new BadCredentialsException("Senha igual ao login.");
        }

        if (!usuario.getSenha().equals(authentication.getCredentials().toString())) {
            throw new BadCredentialsException("Usuário / Senha inválidos.");
        }

        if (usuario.getPermissoes() == null || usuario.getPermissoes().size() == 0) {
            throw new BadCredentialsException("Usuário sem premissões para acesso ao sistema.");
        }

        String token = PasswordEncoder.generateRandomToken(authentication.getName());

        return new LawyerAuthenticationToken(authentication.getName(), authentication.getCredentials(), usuario, getAuthorities(usuario.getPermissoes()), token);
    }

    /**
     * Obtem todas as permiss?es deste Usuário e insere como authorities
     *
     * @param permissoes
     * @return List<GrantedAuthority>
     */
    Collection<? extends GrantedAuthority> getAuthorities (List<Permissao> permissoes) {

        List<GrantedAuthority> authList = new ArrayList<>();
        for (Permissao permissao : permissoes) {
            authList.add(new SimpleGrantedAuthority(permissao.getValue()));
        }
        return authList;
    }

}