package br.com.lawyer.web.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Deividi
 * @since 08/11/2013
 */
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity (prePostEnabled=true)
public class LawyerSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure (WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/assets/**", "/errors/js/**", "/assets-login/**");

    }

//    @Override
//    protected void configure (HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/secure/**", "/api/**").hasAnyRole("ROLE_USER", "ROLE_MANAGER")
//                .anyRequest()
//                .authenticated();
//
//        http.formLogin()
//                .loginPage("/")
//                .loginProcessingUrl("/auth/authenticate")
//                .defaultSuccessUrl("/")
//                .permitAll()
//                .and().logout().logoutSuccessUrl("/")
//                .deleteCookies("JSESSIONID");
//
//        http.authorizeRequests().antMatchers("/*", "/auth/**").anonymous();


//    }

}
