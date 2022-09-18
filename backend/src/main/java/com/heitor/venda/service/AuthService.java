package com.heitor.venda.service;

import com.heitor.venda.domain.Cliente;
import com.heitor.venda.exceptions.ObjectNotFoundExceptions;
import com.heitor.venda.service.email.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    private final ClienteService clienteService;
    private final BCryptPasswordEncoder pe;
    private final EmailSenderService emailSenderService;

    @Autowired
    public AuthService(ClienteService clienteService, BCryptPasswordEncoder pe, EmailSenderService emailSenderService) {
        this.clienteService = clienteService;
        this.pe = pe;
        this.emailSenderService = emailSenderService;
    }

    public void enviaNovaSenha(String email){
        Cliente cliente = clienteService.findByEmail(email);
        if(cliente == null){throw new ObjectNotFoundExceptions("EMAIL NAO ENCONTRADO: " + email);}

        Random random = new Random();
        String senha = "";

        for(int i = 0 ; i < 10; i++){
            int opt = random.nextInt(3);
            switch (opt){
                case 0: //GERAR LETRA MAIUSCULA
                    senha += (char) (random.nextInt(26) + 65);
                    break;
                case 1://GERAR LETRA MINUSCULA
                    senha += (char) (random.nextInt(26) + 97);
                    break;
                case 2://GERAR NUMERO
                    senha += (char) (random.nextInt(10) + 48);
                    break;
            }
        }

        cliente.setSenha(pe.encode(senha));
        clienteService.save(cliente);

        emailSenderService.enviaEmail(cliente.getEmail(), "RECUPERANDO SENHA", "Sua nova senha é: " + senha);
    }
}
