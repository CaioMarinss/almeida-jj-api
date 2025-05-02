package br.com.almeidaPresenca.almeidaPresenca.controllers;

import br.com.almeidaPresenca.almeidaPresenca.dto.RegisterRequestDTO;
import br.com.almeidaPresenca.almeidaPresenca.dto.ResponseDTO;
import br.com.almeidaPresenca.almeidaPresenca.infra.security.TokenService;
import br.com.almeidaPresenca.almeidaPresenca.models.Administrador;
import br.com.almeidaPresenca.almeidaPresenca.repository.AdministradorRepository;
import br.com.almeidaPresenca.almeidaPresenca.dto.LoginRequestDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AdministradorRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        Administrador administrador = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));

        if (body.senha()== null) {
            throw new IllegalArgumentException("A senha não pode ser nula");
        }

        if(passwordEncoder.matches(body.senha(), administrador.getSenha())) {
            String token = this.tokenService.generateToken(administrador);
            return ResponseEntity.ok(new ResponseDTO(administrador.getNome(), token));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body){
        Optional<Administrador> administrador = this.repository.findByEmail(body.email());

        if(administrador.isEmpty()) {
            Administrador newAdm = new Administrador();

            newAdm.setNome(body.nome());
            newAdm.setEmail(body.email());
            newAdm.setSenha(passwordEncoder.encode(body.senha()));

            this.repository.save(newAdm);

            String token = this.tokenService.generateToken(newAdm);
            return ResponseEntity.ok(new ResponseDTO(newAdm.getNome(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}
