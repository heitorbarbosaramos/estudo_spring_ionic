package com.heitor.venda.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
@Profile("dev")
public class ProfileDevConfig {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String estrategiaBanco;

    @Autowired
    private DBinstanciando db;

    @Bean
    public boolean iniciandoProfileTest(){

        log.info("PROFIFE DEV");

        if(!estrategiaBanco.equals("create")){
            return false;
        }

        db.criarCategorias();
        db.criarProduto();

        return true;
    }
}
