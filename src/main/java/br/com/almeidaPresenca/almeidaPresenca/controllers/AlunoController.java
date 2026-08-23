package br.com.almeidaPresenca.almeidaPresenca.controllers;

import br.com.almeidaPresenca.almeidaPresenca.dao.AlunoDAO;
import br.com.almeidaPresenca.almeidaPresenca.dto.ResetSenhaDTO;
import br.com.almeidaPresenca.almeidaPresenca.enums.Errors;
import br.com.almeidaPresenca.almeidaPresenca.enums.SituacaoAtivoInativo;
import br.com.almeidaPresenca.almeidaPresenca.infra.security.TokenService;
import br.com.almeidaPresenca.almeidaPresenca.models.AlunoVO;
import br.com.almeidaPresenca.almeidaPresenca.services.AlunoService;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

  @Autowired private AlunoDAO alunoDAO;

  @Autowired private AlunoService alunoService;

  @Autowired private TokenService tokenService;

  @GetMapping("/listar")
  //  url/aluno/listar
  public ResponseEntity<List<AlunoVO>> obterTodosAlunosAtivos() {
    List<AlunoVO> alunoVOS = alunoDAO.obterTodosAlunosAtivos();
    return ResponseEntity.ok().body(alunoVOS);
  }

  @PostMapping("/inserir")
  // inserindo aluno
  public ResponseEntity<AlunoVO> insertNew(@RequestBody AlunoVO alunoVO) {
    AlunoVO alunoVOInserido = alunoService.insertAluno(alunoVO);
    return ResponseEntity.ok(alunoVOInserido);
  }

  @PutMapping("/resetar")
  public ResponseEntity<?> resetPassword(@RequestBody ResetSenhaDTO body) {
    try {
      alunoService.resetPassword(body);
      return ResponseEntity.ok(Map.of("mensagem", "Senha redefinida com sucesso"));
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
  }

  @GetMapping("/verificar")
  public ResponseEntity<Object> verificarEmail(@RequestParam String token) {
    try {
      String email = tokenService.validateTokenAndGetEmail(token);

      AlunoVO alunoVO = alunoDAO.obterPorEmail(email);
      if (Objects.isNull(alunoVO)) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(Map.of(Errors.ERR001.getValue(), Errors.ERR001.getDescricao()));
      }

      alunoVO.setSituacao(SituacaoAtivoInativo.ATIVO.getValue());
      alunoService.atualizaSitAluno(alunoVO.getIdAluno(), SituacaoAtivoInativo.ATIVO);
      return ResponseEntity.ok("E-mail verificado com sucesso!");

    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
