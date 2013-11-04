package br.com.lawyer.core.authentication;

import br.com.lawyer.core.entity.Usuario;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Implementacao do <code>AbstractAuthenticationToken</code> para ser salvo na sessao do usuario. Com ele eh possivel identificar se o objeto de autenticacao que esta na sessao eh valido
 * e salvar o objeto proprio de autenticacao do Lawyer
 */
public class LawyerAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 7233674450419659778L;

    private final Object principal;
    private Object credentials;

    private String token;

    private Usuario usuario;

    //~ Constructors ===================================================================================================

    /**
     * This constructor can be safely used by any code that wishes to create a
     * <code>{@link LawyerAuthenticationToken}</code>, as the {@link
     * #isAuthenticated()} will return <code>false</code>.
     */
    public LawyerAuthenticationToken (Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(false);
    }

    /**
     * This constructor should only be used by <code>AuthenticationManager</code> or <code>AuthenticationProvider</code>
     * implementations that are satisfied with producing a trusted (i.e. {@link #isAuthenticated()} = <code>true</code>)
     * authentication token.
     *
     * @param principal
     * @param credentials
     * @param authorities
     */
    public LawyerAuthenticationToken (Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true); // must use super, as we override
    }

    public LawyerAuthenticationToken (Object principal, Object credentials, Usuario user, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        this.usuario = user;
        super.setAuthenticated(true); // must use super, as we override
    }

    public LawyerAuthenticationToken (Object principal, Object credentials, Usuario user, Object details, Collection<? extends GrantedAuthority> authorities, String token) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        this.usuario = user;
        this.token = token;
        this.setDetails(details);
        super.setAuthenticated(true); // must use super, as we override
    }

    public LawyerAuthenticationToken (Object principal, Object credentials, Usuario usuario, Collection<? extends GrantedAuthority> authorities, String token) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        this.usuario = usuario;
        this.token = token;
        super.setAuthenticated(true); // must use super, as we override
    }

    public LawyerAuthenticationToken (Object principal, Object credentials, Usuario usuario) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        this.usuario = usuario;
        super.setAuthenticated(false);
    }


    public Object getCredentials () {
        return this.credentials;
    }

    public Object getPrincipal () {
        return this.principal;
    }

    public Usuario getUsuario () {
        return this.usuario;
    }

    // Se for setar "na mao", lanca a excecao, pois este metodo so pode ser alterado com o super() do pai.
    public void setAuthenticated (boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Năo é possível atribuir este Token como confiável - use o construtor que receba a lista de GrantedAuthority.");
        }

        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials () {
        super.eraseCredentials();
        credentials = null;
    }

    public String getToken () {
        return token;
    }


}