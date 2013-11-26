package br.com.lawyer.web.controller;

import br.com.lawyer.core.authentication.LawyerAuthenticationToken;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.delegate.UsuarioDelegate;
import br.com.lawyer.web.vo.UsuarioVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Deividi Cavarzan
 */
@Controller
public class AuthenticationController {

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioDelegate usuarioDelegate;

    /**
     * Esse é o mapeamento que o Spring Security redireciona quando o usuário acessa um contexto protegido por ele.
     * Foi criado esse mapeamento para a URL fica amigável, /auth, ao invés de /auth/login.html
     * @return {String} pagina de login
     */
    @RequestMapping(value = "/auth/**", method = RequestMethod.GET)
    public String auth() {
        return "authentication/index";
    }

    /**
     * Realiza a autenticação na pagina de login
     * @return {@link ResponseEntity} {@link UsuarioVO} com o status HTTP.
     */
    @RequestMapping(value = "/auth/authenticate", method = RequestMethod.POST)
    public ResponseEntity<UsuarioVO> autenticaUsuario(@RequestBody UsuarioVO usuarioVO) throws BusinessException {
        UsuarioVO autenticacao = usuarioDelegate.authenticate(usuarioVO, authenticationManager);
        LawyerAuthenticationToken token = (LawyerAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        autenticacao.setToken(token.getToken());
        return new ResponseEntity<>(autenticacao, HttpStatus.OK);
    }


}