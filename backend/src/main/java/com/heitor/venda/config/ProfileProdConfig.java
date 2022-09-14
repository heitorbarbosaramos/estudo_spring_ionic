package com.heitor.venda.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
@Profile("prod")
public class ProfileProdConfig {

    @Autowired
    private DBinstanciando db;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String estrategiaBanco;

    @Bean
    public boolean iniciandoProfileTest(){

        log.info("PROFIFE PROD");

        if(!estrategiaBanco.equals("create")){
            return false;
        }

        db.criarCategorias();
        db.criarProduto();
        db.criarCliente();

        return true;
    }
}
