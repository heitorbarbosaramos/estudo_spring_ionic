package com.heitor.venda.seguranca;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

    public static SpringUserSecurity authentication(){
        try {
            return (SpringUserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (Exception e){
            return null;
        }
    }
}
