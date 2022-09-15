package com.heitor.venda.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
@Profile("test")
public class ProfileTestConfig {

    @Autowired
    private DBinstanciando db;

    @Bean
    public void iniciandoProfileTest(){

        log.info("ENTROU EM TESTES");

        db.criarCategorias();
        db.criarProduto();
        db.criarCliente();
    }
}
