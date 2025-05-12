package br.com.almeidaPresenca.almeidaPresenca.controllers;

import br.com.almeidaPresenca.almeidaPresenca.dto.EmailDTO;
import br.com.almeidaPresenca.almeidaPresenca.dto.RegisterRequestDTO;
import br.com.almeidaPresenca.almeidaPresenca.dto.ResponseDTO;
import br.com.almeidaPresenca.almeidaPresenca.infra.security.TokenService;
import br.com.almeidaPresenca.almeidaPresenca.models.Administrador;
import br.com.almeidaPresenca.almeidaPresenca.repository.AdministradorRepository;
import br.com.almeidaPresenca.almeidaPresenca.dto.LoginRequestDTO;

import br.com.almeidaPresenca.almeidaPresenca.services.AdministradorService;
import br.com.almeidaPresenca.almeidaPresenca.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AdministradorService repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final EmailService emailService;



    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        Administrador administrador = this.repository.findByEmailIgnoreCase(body.email()).orElseThrow(() -> new RuntimeException("User not found"));

        if (body.senha()== null) {
            throw new IllegalArgumentException("A senha não pode ser nula");
        }

        if (!administrador.isVerificado()) {
            return ResponseEntity.status(403).body("E-mail não verificado. Verifique seu e-mail antes de fazer login.");
        }

        if (passwordEncoder.matches(body.senha(), administrador.getSenha())) {
            String token = this.tokenService.generateToken(administrador);
            return ResponseEntity.ok(new ResponseDTO(administrador.getNome(), token));
        }


        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body) {
        Optional<Administrador> administrador = this.repository.findByEmailIgnoreCase(body.email());

        if (administrador.isEmpty()) {
            Administrador newAdm = new Administrador();

            newAdm.setNome(body.nome());
            newAdm.setEmail(body.email());
            newAdm.setSenha(passwordEncoder.encode(body.senha()));
            newAdm.setVerificado(false);  // Certificando-se de que ele não está verificado ainda

            this.repository.save(newAdm);

            emailService.sendEmailVerification(newAdm);

            return ResponseEntity.ok(Map.of("mensagem", "Cadastro realizado com sucesso! Verifique seu e-mail para ativação"));
        }
        return ResponseEntity.badRequest().body(Map.of("erro", "E-mail já cadastrado"));
    }

    @PostMapping("/enviar-email-recuperacao")
    public ResponseEntity<?> forgotPassword(@RequestBody EmailDTO body) {
        String email = body.email();
        Administrador administrador = this.repository
                .findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        try {
            emailService.sendPasswordResetEmail(email);
            return ResponseEntity.ok(Map.of("mensagem", "Email de recuperação enviado com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", "Erro ao enviar o email"));
        }
    }

    @PostMapping("/enviar-email-verificacao")
    public ResponseEntity<?> verifyEmail(@RequestBody EmailDTO body) {
        String email = body.email();
        Administrador administrador = this.repository
                .findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        try {
            emailService.sendEmailVerification(administrador);
            return ResponseEntity.ok(Map.of("mensagem", "Email de verificação enviado com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", "Erro ao enviar o email"));
        }
    }




}
