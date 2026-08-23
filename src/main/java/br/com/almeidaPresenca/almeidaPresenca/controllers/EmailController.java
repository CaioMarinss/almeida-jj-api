package br.com.almeidaPresenca.almeidaPresenca.controllers;

import br.com.almeidaPresenca.almeidaPresenca.dao.AlunoDAO;
import br.com.almeidaPresenca.almeidaPresenca.dto.EmailDTO;
import br.com.almeidaPresenca.almeidaPresenca.enums.Errors;
import br.com.almeidaPresenca.almeidaPresenca.enums.MsgSucesso;
import br.com.almeidaPresenca.almeidaPresenca.models.AlunoVO;
import br.com.almeidaPresenca.almeidaPresenca.services.EmailService;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

  private final AlunoDAO alunoDAO;
  private final EmailService emailService;

  @PostMapping("/enviar-email-recuperacao")
  public ResponseEntity<?> forgotPassword(@RequestBody EmailDTO body) {
    AlunoVO alunoVO = alunoDAO.obterPorEmail(body.email());
    if (Objects.isNull(alunoVO)) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body(Map.of(Errors.ERR001.getValue(), Errors.ERR001.getDescricao()));
    }

    try {
      emailService.sendPasswordResetEmail(body.email());
      return ResponseEntity.ok(Map.of(MsgSucesso.SUC002, MsgSucesso.SUC002.getDescricao()));
    } catch (Exception e) {
      return ResponseEntity.badRequest()
          .body(Map.of(Errors.ERR006.getValue(), Errors.ERR006.getDescricao()));
    }
  }

  @PostMapping("/enviar-email-verificacao")
  public ResponseEntity<?> verifyEmail(@RequestBody EmailDTO body) {

    AlunoVO alunoVO = alunoDAO.obterPorEmail(body.email());
    if (Objects.isNull(alunoVO)) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body(Map.of(Errors.ERR001.getValue(), Errors.ERR001.getDescricao()));
    }

    try {
      emailService.sendEmailVerification(alunoVO);
      return ResponseEntity.ok(Map.of(MsgSucesso.SUC003, MsgSucesso.SUC003.getDescricao()));
    } catch (Exception e) {
      return ResponseEntity.badRequest()
          .body(Map.of(Errors.ERR006.getValue(), Errors.ERR006.getDescricao()));
    }
  }
}
