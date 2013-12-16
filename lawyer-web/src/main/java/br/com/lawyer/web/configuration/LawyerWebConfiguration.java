package br.com.lawyer.web.configuration;

import br.com.lawyer.core.configuration.LawyerCoreConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author Deividi
 * @since 07/11/2013
 */

@EnableWebMvc
@Configuration
@Import({LawyerCoreConfiguration.class, LawyerSecurityConfiguration.class })
@ComponentScan ({"br.com.lawyer.web"})
@ImportResource({"/WEB-INF/spring-context-security.xml"})
public class LawyerWebConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix( "/" );
        viewResolver.setSuffix( ".html" );

        return viewResolver;
    }

    @Bean
    public RequestMappingHandlerMapping mapping() {
        return new RequestMappingHandlerMapping();
    }


    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("/secure/assets/");
        registry.addResourceHandler("/assets-login/**").addResourceLocations("/authentication/assets-login/");
        registry.addResourceHandler("/src/**").addResourceLocations("/secure/src/");
        registry.addResourceHandler("/vendor/**").addResourceLocations("/secure/vendor/");
        registry.addResourceHandler("/errors/js/**").addResourceLocations("/errors/js/");
    }

    @Bean
    public MappingJackson2HttpMessageConverter jacksonMessageConverter() {
        MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJacksonHttpMessageConverter.setJsonPrefix(")]}',\n");
        return mappingJacksonHttpMessageConverter;
    }

}
