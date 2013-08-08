package test;

import br.com.lawyer.core.authentication.LawyerAuthenticationToken;
import br.com.lawyer.core.util.PasswordEncoder;
import br.com.lawyer.web.vo.AuthenticationVO;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-context-test.xml")
@TransactionConfiguration (transactionManager="transactionManager", defaultRollback=false)
public class AuthenticationTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    @Qualifier(value = "authenticationManager")
    private AuthenticationManager authenticationManager;

    @Test
    public void authenticate() {
        AuthenticationVO authenticationVO = new AuthenticationVO();

        authenticationVO.setEmail("developer@lawyer.com.br");
        authenticationVO.setSenha("123");

        String password = PasswordEncoder.encodePassword(authenticationVO.getSenha(), authenticationVO.getEmail());

        Authentication auth = new LawyerAuthenticationToken(authenticationVO.getEmail(), password);
        auth = authenticationManager.authenticate(auth);

        Assert.assertEquals(auth.isAuthenticated(), true);

    }

}
