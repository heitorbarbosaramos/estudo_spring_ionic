package com.heitor.venda.resource;

import com.heitor.venda.seguranca.JWTUtil;
import com.heitor.venda.seguranca.SpringUserSecurity;
import com.heitor.venda.seguranca.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthResouces {

    private final JWTUtil jwtUtil;

    @Autowired
    public AuthResouces(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public ResponseEntity<Void> refreshToken(HttpServletResponse response){
        SpringUserSecurity userSecurity = UserService.authentication();
        String token = jwtUtil.gerandoToken(userSecurity.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }
}
