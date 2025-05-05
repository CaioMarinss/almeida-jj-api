package br.com.almeidaPresenca.almeidaPresenca.controllers;

import br.com.almeidaPresenca.almeidaPresenca.dto.ResetSenhaDTO;
import br.com.almeidaPresenca.almeidaPresenca.models.Administrador;
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

    @GetMapping("/listar")
    public ResponseEntity<List<Administrador>> findAll() {
        List<Administrador> administradores = administradorService.findAll();
        return ResponseEntity.ok().body(administradores); // se der ok, ele lista os administradores no body do json
    }

    // listar pelo ID
    @GetMapping("/{idAdministrador}")
    public ResponseEntity<Administrador> findById(@PathVariable Integer idAdministrador) {
        Administrador administrador = administradorService.findById(idAdministrador);
        return ResponseEntity.ok(administrador);
    }

    // inserindo administrador
    @PostMapping("/inserir")
    public ResponseEntity<Administrador> insertNew(@RequestBody Administrador administrador) {
        Administrador administradorInserido = administradorService.insertNewAdministrador(administrador);
        return ResponseEntity.ok(administradorInserido);
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

    // deletar administrador
    @DeleteMapping("/{idAdministrador}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Integer idAdministrador) {
        Boolean flag = administradorService.deleteById(idAdministrador);
        return ResponseEntity.ok().body(flag);
    }
}
