package br.com.almeidaPresenca.almeidaPresenca.controllers;

import br.com.almeidaPresenca.almeidaPresenca.models.Aluno;
import br.com.almeidaPresenca.almeidaPresenca.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
//controler diz respeito a tudo que o usuario controla
public class AlunoController {

    @Autowired
    private AlunoService alunoService; // instancio um new alunoService ja que vou precisar usar os metodos de la

    @GetMapping("/listar")
    //  url/aluno/listar
    public ResponseEntity <List<Aluno>> findAll(){ //O corpo da resposta (body) será do tipo List<Aluno>, ou seja, uma lista de objetos Aluno
        List<Aluno> alunos = alunoService.findAll();
        return ResponseEntity.ok().body(alunos);//se der ok, ele lista os produtos no body do json

    }

    //listar pelo ID
    @GetMapping("/{idAluno}")
    public ResponseEntity <Aluno>findById(@PathVariable Integer idAluno){
        Aluno aluno = alunoService.findById(idAluno);
        return ResponseEntity.ok(aluno); //sempre retorna um responseEntity
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