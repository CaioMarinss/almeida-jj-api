package br.com.almeidaPresenca.almeidaPresenca.controllers;

import br.com.almeidaPresenca.almeidaPresenca.models.Aluno;
import br.com.almeidaPresenca.almeidaPresenca.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/listar")
    //  url/aluno/listar
    public ResponseEntity <List<Aluno>> findAll(){
        List<Aluno> alunos = alunoService.findAll();
        return ResponseEntity.ok().body(alunos);

    }

    //listar pelo ID
    @GetMapping("/{idAluno}")
    public ResponseEntity <Aluno>findById(@PathVariable Integer idAluno){
        Aluno aluno = alunoService.findById(idAluno);
        return ResponseEntity.ok(aluno);
    }

    @PostMapping("/inserir")
    //inserindo aluno
    public ResponseEntity<Aluno> insertNew(@RequestBody Aluno aluno){
        Aluno alunoInserido = alunoService.insertNewAluno(aluno);
        return ResponseEntity.ok(alunoInserido);
    }

    @PutMapping("/{idAluno}")
    public ResponseEntity<Aluno> update(@PathVariable Integer idAluno, @RequestBody Aluno alunoAlterado) {
        Aluno aluno = alunoService.update(idAluno, alunoAlterado);
        return ResponseEntity.ok().body(aluno);
    }

    @DeleteMapping("/{idAluno}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Integer idAluno){
        Boolean flag = alunoService.deleteById(idAluno);
        return  ResponseEntity.ok().body(flag);
    }
}