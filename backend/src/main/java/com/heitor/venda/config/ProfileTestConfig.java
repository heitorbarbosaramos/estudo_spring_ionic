package com.heitor.venda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class ProfileTestConfig {

    @Bean
    public void iniciandoProfileTest(){
        System.out.println("ENTROU EM TESTES");
    }
}
