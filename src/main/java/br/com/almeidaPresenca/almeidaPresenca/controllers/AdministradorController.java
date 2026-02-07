package br.com.almeidaPresenca.almeidaPresenca.controllers;

import br.com.almeidaPresenca.almeidaPresenca.SituacaoAtivoInativo;
import br.com.almeidaPresenca.almeidaPresenca.dto.ResetSenhaDTO;
import br.com.almeidaPresenca.almeidaPresenca.infra.security.TokenService;
import br.com.almeidaPresenca.almeidaPresenca.models.AdministradorVO;
import br.com.almeidaPresenca.almeidaPresenca.services.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/administrador")

public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;
    @Autowired
    private TokenService tokenService;

    @GetMapping("/listar")
    public ResponseEntity<List<AdministradorVO>> findAll() {
        List<AdministradorVO> administradores = administradorService.findAll();
        return ResponseEntity.ok().body(administradores); // se der ok, ele lista os administradores no body do json
    }

    // listar pelo ID
    @GetMapping("/{idAdministrador}")
    public ResponseEntity<AdministradorVO> findById(@PathVariable Integer idAdministrador) {
        AdministradorVO administradorVO = administradorService.findById(idAdministrador);
        return ResponseEntity.ok(administradorVO);
    }


    @PutMapping("/resetar")
    public ResponseEntity<?> resetPassword(@RequestBody ResetSenhaDTO body) {
        try {
            administradorService.resetPassword(body);
            return ResponseEntity.ok(Map.of("mensagem", "Senha redefinida com sucesso"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/verificar")
    public ResponseEntity<String> verificarEmail(@RequestParam String token) {
        try {
            String email = tokenService.validateTokenAndGetEmail(token);

            AdministradorVO administradorVO = administradorService.findByEmailIgnoreCase(email)
                    .orElseThrow(() -> new RuntimeException("Administrador não encontrado"));

            administradorVO.setSituacao(SituacaoAtivoInativo.ATIVO.getValue());
            administradorService.save(administradorVO);
            return ResponseEntity.ok("E-mail verificado com sucesso!");

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // deletar administrador
    @DeleteMapping("/{idAdministrador}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Integer idAdministrador) {
        Boolean flag = administradorService.deleteById(idAdministrador);
        return ResponseEntity.ok().body(flag);
    }
}
