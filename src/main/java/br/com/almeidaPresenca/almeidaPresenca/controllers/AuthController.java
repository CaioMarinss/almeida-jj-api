package br.com.almeidaPresenca.almeidaPresenca.controllers;

import br.com.almeidaPresenca.almeidaPresenca.dao.AlunoDAO;
import br.com.almeidaPresenca.almeidaPresenca.enums.Errors;
import br.com.almeidaPresenca.almeidaPresenca.enums.MsgSucesso;
import br.com.almeidaPresenca.almeidaPresenca.enums.SituacaoAtivoInativo;
import br.com.almeidaPresenca.almeidaPresenca.dto.EmailDTO;
import br.com.almeidaPresenca.almeidaPresenca.dto.RegisterRequestDTO;
import br.com.almeidaPresenca.almeidaPresenca.dto.ResponseDTO;
import br.com.almeidaPresenca.almeidaPresenca.infra.security.TokenService;
import br.com.almeidaPresenca.almeidaPresenca.dto.LoginRequestDTO;

import br.com.almeidaPresenca.almeidaPresenca.models.AlunoVO;
import br.com.almeidaPresenca.almeidaPresenca.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AlunoDAO alunoDAO;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final EmailService emailService;



    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){

        AlunoVO alunoVO = alunoDAO.obterPorEmail(body.email());
        if (Objects.isNull(alunoVO)){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(Errors.ERR001.getValue(), Errors.ERR001.getDescricao()));
        }

        if (body.senha() == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(Errors.ERR002.getValue(), Errors.ERR002.getDescricao()));
        }

        if (alunoVO.getSituacao().equals(SituacaoAtivoInativo.INATIVO.getValue())) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(Errors.ERR003.getValue(), Errors.ERR003.getDescricao()));
        }

        if (passwordEncoder.matches(body.senha(), alunoVO.getSenha())) {
            String token = this.tokenService.generateToken(alunoVO);
            return ResponseEntity.ok(new ResponseDTO(alunoVO.getNome(), token));
        }

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Map.of(Errors.ERR004.getValue(), Errors.ERR004.getDescricao()));
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body) {

        AlunoVO alunoVO = alunoDAO.obterPorEmail(body.email());
        if (Objects.nonNull(alunoVO)){
            return ResponseEntity.badRequest().body(Map.of(Errors.ERR005.getValue(), Errors.ERR005.getDescricao()));
        }

        AlunoVO novoAluno = new AlunoVO();

        novoAluno.setNome(body.nome());
        novoAluno.setCpf(body.cpf());
        novoAluno.setEmail(body.email());
        novoAluno.setSenha(passwordEncoder.encode(body.senha()));
        novoAluno.setIdGraduacao(body.idGraduacao()); // ta notnull no banco
        novoAluno.setSituacao(SituacaoAtivoInativo.INATIVO.getValue());  // Certificando-se de que ele não está verificado ainda
        novoAluno.setIdGraduacao(body.idplano()); // ta notnull no banco

        alunoDAO.insertAluno(novoAluno);

        emailService.sendEmailVerification(novoAluno);

        return ResponseEntity.ok(Map.of(MsgSucesso.SUC001, MsgSucesso.SUC001.getDescricao()));
    }

    @PostMapping("/enviar-email-recuperacao")
    public ResponseEntity<?> forgotPassword(@RequestBody EmailDTO body) {
        AlunoVO alunoVO = alunoDAO.obterPorEmail(body.email());
        if (Objects.isNull(alunoVO)){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(Errors.ERR001.getValue(), Errors.ERR001.getDescricao()));
        }

        try {
            emailService.sendPasswordResetEmail(body.email());
            return ResponseEntity.ok(Map.of(MsgSucesso.SUC002, MsgSucesso.SUC002.getDescricao()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(Errors.ERR006.getValue(), Errors.ERR006.getDescricao()));
        }
    }

    @PostMapping("/enviar-email-verificacao")
    public ResponseEntity<?> verifyEmail(@RequestBody EmailDTO body) {

        AlunoVO alunoVO = alunoDAO.obterPorEmail(body.email());
        if (Objects.isNull(alunoVO)){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(Errors.ERR001.getValue(), Errors.ERR001.getDescricao()));
        }

        try {
            emailService.sendEmailVerification(alunoVO);
            return ResponseEntity.ok(Map.of(MsgSucesso.SUC003, MsgSucesso.SUC003.getDescricao()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(Errors.ERR006.getValue(), Errors.ERR006.getDescricao()));
        }
    }

}
