package br.com.almeidaPresenca.almeidaPresenca.services;

import br.com.almeidaPresenca.almeidaPresenca.dao.AlunoDAO;
import br.com.almeidaPresenca.almeidaPresenca.enums.Errors;
import br.com.almeidaPresenca.almeidaPresenca.infra.security.TokenService;
import br.com.almeidaPresenca.almeidaPresenca.models.AlunoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private AlunoDAO alunoDAO;

    @Autowired
    private TokenService tokenService;

    private String link = "https://almeida-jj-api.onrender.com/auth";
    private String linkFront = "https://almeidatucuruvi.vercel.app";

    public void enviarEmail(String para, String assunto, String corpo) {
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(para);
        mensagem.setSubject(assunto);
        mensagem.setText(corpo);
        mensagem.setFrom("devmarins@gmail.com");

        mailSender.send(mensagem);
    }

    public void sendPasswordResetEmail(String email) {
        AlunoVO alunoVO;
        try{
            alunoVO = alunoDAO.obterPorEmail(email);
        } catch (Exception e){
            throw new RuntimeException("Aluno não encontrado: " + e.getMessage());
        }

        String token = tokenService.generateToken(alunoVO);

        String resetLink = link + "/resetar?token=" + token + "&email=" + email;

        String subject = "Recuperação de Senha";
        String message = "Clique no link abaixo para redefinir sua senha:\n" + resetLink;

        enviarEmail(email, subject, message);
    }

    public void sendEmailVerification(AlunoVO alunoVO) {
        String email = alunoVO.getEmail();
        String nome = alunoVO.getNome();

        String token = tokenService.generateToken(alunoVO);
        String envLink = linkFront + "/verificar?token=" + token;

        String mensagem = "Clique no envLink para verificar seu e-mail:\n\n" + envLink;

        enviarEmail(alunoVO.getEmail(), "Verificação de E-mail", mensagem);
    }
}
