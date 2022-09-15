package com.heitor.venda.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static String[] PUBLIC_MATCHERS = {"/h2-console/**"};
    private static String[] PUBLIC_MATCHERS_GET = {"/categoria/**", "/produto/**"};

    @Autowired
    private Environment env;

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        if(Arrays.asList(env.getActiveProfiles()).contains("test")){
            http.headers().frameOptions().disable();
        }

        http.cors().and().csrf().disable();//COMO A APLICACAO É STATELLES, NAO ARMAZENA SESSAO, ESTOU DESABILITANDO

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//PARA ASSEGURAR QUE O BACK NÃO ARMAZENE SESSAO

        http.authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
                .anyRequest().authenticated();

    }

    //CONFIGURACAO DE CORS PARA ACESSO DE MULTPLAS FONTES
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues()); //PERMITINDO ACESSO DE MULTIPLAS FONTES COM CONFIGURACOES BASICAS
        return source;
    }
}
