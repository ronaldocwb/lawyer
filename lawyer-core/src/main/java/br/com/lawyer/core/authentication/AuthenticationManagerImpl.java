package br.com.lawyer.core.authentication;

import br.com.lawyer.core.entity.Permissao;
import br.com.lawyer.core.entity.Usuario;
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
 * Servico que realiza a autenticação do Spring security e cria o {@link br.com.lawyer.core.authentication.LawyerAuthenticationToken} para armazenamento na sessão - {SecurityContextHolder}.
 */
@Service("authenticationManager")
public class AuthenticationManagerImpl implements AuthenticationManager {

    /**
     * Realiza a autenticacao e atualiza o objeto Authetication
     * @param authentication
     */
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        LawyerAuthenticationToken lawyerAuthenticationToken = (LawyerAuthenticationToken) authentication;
        Usuario usuario = lawyerAuthenticationToken.getUsuario();

        if (authentication.getName().equals(authentication.getCredentials())) {
            throw new BadCredentialsException("Usuário / Senha inválidos.");
        }

        if (authentication.getName() == null) {
            throw new BadCredentialsException("Usuário n?o informado.");
        }

        if (authentication.getName().equals(authentication.getCredentials())) {
            throw new BadCredentialsException("Senha igual ao login.");
        }

        if (!usuario.getSenha().equals(authentication.getCredentials().toString())) {
            throw new BadCredentialsException("Usuário / Senha inválidos.");
        }

        String token = PasswordEncoder.generateRandomToken(authentication.getName());

        return new LawyerAuthenticationToken(authentication.getName(), authentication.getCredentials(), usuario, getAuthorities(usuario.getPermissoes()), token);
    }

    /**
     * Obtem todas as permiss?es deste usuário e insere como authorities
     * @param permissoes
     * @return List<GrantedAuthority>
     */
    public Collection<? extends GrantedAuthority> getAuthorities(List<Permissao> permissoes) {

        List<GrantedAuthority> authList = new ArrayList<>();
        for (Permissao permissao : permissoes) {
            authList.add(new SimpleGrantedAuthority(permissao.toString()));
        }
        return authList;
    }

}