package br.com.almeidaPresenca.almeidaPresenca.controllers;

import br.com.almeidaPresenca.almeidaPresenca.models.Administrador;
import br.com.almeidaPresenca.almeidaPresenca.services.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrador")
// controller diz respeito a tudo que o usuário controla
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService; // instancio um new administradorService já que vou precisar usar os métodos de lá

    @GetMapping("/listar")
    // url/administrador/listar
    public ResponseEntity<List<Administrador>> findAll() {
        List<Administrador> administradores = administradorService.findAll();
        return ResponseEntity.ok().body(administradores); // se der ok, ele lista os administradores no body do json
    }

    // listar pelo ID
    @GetMapping("/{idAdministrador}")
    public ResponseEntity<Administrador> findById(@PathVariable Integer idAdministrador) {
        Administrador administrador = administradorService.findById(idAdministrador);
        return ResponseEntity.ok(administrador); // sempre retorna um responseEntity
    }

    // inserindo administrador
    @PostMapping("/inserir")
    public ResponseEntity<Administrador> insertNew(@RequestBody Administrador administrador) {
        Administrador administradorInserido = administradorService.insertNewAdministrador(administrador);
        return ResponseEntity.ok(administradorInserido);
    }

    // atualizar administrador
    @PutMapping("/{idAdministrador}")
    public ResponseEntity<Administrador> update(@PathVariable Integer idAdministrador, @RequestBody Administrador administradorAlterado) {
        Administrador administrador = administradorService.update(idAdministrador, administradorAlterado);
        return ResponseEntity.ok().body(administrador);
    }

    // deletar administrador
    @DeleteMapping("/{idAdministrador}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Integer idAdministrador) {
        Boolean flag = administradorService.deleteById(idAdministrador);
        return ResponseEntity.ok().body(flag);
    }
}
