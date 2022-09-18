package com.heitor.venda.seguranca;

import com.heitor.venda.domain.Cliente;
import com.heitor.venda.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Cliente cli = repo.findByEmail(email);

        if(cli == null){
            throw new UsernameNotFoundException(email);
        }
        return new SpringUserSecurity(cli.getId(), cli.getEmail(), cli.getSenha(), cli.getPerfis());
    }
}
