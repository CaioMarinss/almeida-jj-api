package br.com.almeidaPresenca.almeidaPresenca.services;

import br.com.almeidaPresenca.almeidaPresenca.dao.AlunoDAO;
import br.com.almeidaPresenca.almeidaPresenca.infra.security.TokenService;
import br.com.almeidaPresenca.almeidaPresenca.models.AlunoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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

        AlunoVO alunoVO = alunoDAO.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        String token = tokenService.generateToken(alunoVO);

        String resetLink = link + "/resetar?token=" + token + "&email=" + email;


        String subject = "Recuperação de Senha";
        String message = "Clique no link abaixo para redefinir sua senha:\n" + resetLink;

        enviarEmail(email, subject, message);
    }

    public void sendEmailVerification(AdministradorVO administradorVO) {
        String email = administradorVO.getEmail();
        String nome = administradorVO.getNome();

        String token = tokenService.generateToken(administradorVO);
        String envLink = linkFront + "/verificar?token=" + token;

        String mensagem = "Clique no envLink para verificar seu e-mail:\n\n" + envLink;

        enviarEmail(administradorVO.getEmail(), "Verificação de E-mail", mensagem);
    }
}
