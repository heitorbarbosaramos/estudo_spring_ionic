package com.heitor.venda.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static String[] PUBLIC_MATCHERS = {"/h2-console/**"};
    private static String[] PUBLIC_MATCHERS_GET = {"/categoria/**", "/produto/**", "/util/**"};
    private static String[] PUBLIC_MATCHERS_POST = {"/cliente/**", "/auth/**"};

    @Autowired
    private Environment env;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JWTUtil jwtUtil;

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        if(Arrays.asList(env.getActiveProfiles()).contains("test")){
            http.headers().frameOptions().disable();
        }

        http.cors().and().csrf().disable();//COMO A APLICACAO É STATELLES, NAO ARMAZENA SESSAO, ESTOU DESABILITANDO

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//PARA ASSEGURAR QUE O BACK NÃO ARMAZENE SESSAO

        http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));//AUTENTICACAO DE USUARIO

        http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));//AUTORIZACAO DE USUARIO

        http.authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
                .antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
                .anyRequest().authenticated();

    }

    //CONFIGURACAO DE CORS PARA ACESSO DE MULTPLAS FONTES
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList("POST", "PUT", "DELETE", "GET", "OPTIONS"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); //PERMITINDO ACESSO DE MULTIPLAS FONTES COM CONFIGURACOES BASICAS
        return source;
    }

    //COMPONENTE NA FORMA DE BEAN PARA ENCODAR SENHA EM QUALQUER CLASSE DO SISTEMA
    @Bean
    public BCryptPasswordEncoder byBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(byBCryptPasswordEncoder());
    }
}
