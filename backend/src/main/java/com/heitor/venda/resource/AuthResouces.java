package com.heitor.venda.resource;

import com.heitor.venda.seguranca.JWTUtil;
import com.heitor.venda.seguranca.SpringUserSecurity;
import com.heitor.venda.seguranca.UserService;
import com.heitor.venda.service.AuthService;
import com.heitor.venda.service.util.AuthEmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthResouces {

    private final JWTUtil jwtUtil;
    private final AuthService service;

    @Autowired
    public AuthResouces(JWTUtil jwtUtil, AuthService service) {
        this.jwtUtil = jwtUtil;
        this.service = service;
    }

    @GetMapping("/refresh")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response){
        SpringUserSecurity userSecurity = UserService.authentication();
        String token = jwtUtil.gerandoToken(userSecurity.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/forgot")
    private ResponseEntity<Void> recuperandoSenha(@RequestBody @Valid AuthEmailDTO authEmailDTO){
        service.enviaNovaSenha(authEmailDTO.getEmail());
        return ResponseEntity.noContent().build();
    }
}
