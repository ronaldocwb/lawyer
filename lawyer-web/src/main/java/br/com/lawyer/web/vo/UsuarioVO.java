package br.com.lawyer.web.vo;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class UsuarioVO {

    private String email;

    private String token;

    private List<GrantedAuthority> authorities;

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
