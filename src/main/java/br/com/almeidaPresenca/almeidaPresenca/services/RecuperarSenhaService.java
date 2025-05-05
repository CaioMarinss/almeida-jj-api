package br.com.almeidaPresenca.almeidaPresenca.services;

import br.com.almeidaPresenca.almeidaPresenca.models.Administrador;
import br.com.almeidaPresenca.almeidaPresenca.repository.AdministradorRepository;
import br.com.almeidaPresenca.almeidaPresenca.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecuperarSenhaService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private TokenService tokenService;

    public void sendPasswordResetEmail(String email) {

        Administrador administrador = administradorRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("Administrador não encontrado"));

        String token = tokenService.generateToken(administrador);

        String resetLink = "http://localhost:4200/resetar?token=" + token + "&email=" + email;


        String subject = "Recuperação de Senha";
        String message = "Clique no link abaixo para redefinir sua senha:\n" + resetLink;

        emailService.enviarEmail(email, subject, message);
    }
}
