package br.com.almeidaPresenca.almeidaPresenca.controllers;

import br.com.almeidaPresenca.almeidaPresenca.dao.AlunoDAO;
import br.com.almeidaPresenca.almeidaPresenca.models.AlunoVO;
import br.com.almeidaPresenca.almeidaPresenca.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class  AlunoController {

    @Autowired
    private AlunoDAO alunoDAO;

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/listar")
    //  url/aluno/listar
    public ResponseEntity <List<AlunoVO>> obterTodosAlunosAtivos(){
        List<AlunoVO> alunoVOS = alunoDAO.obterTodosAlunosAtivos();
        return ResponseEntity.ok().body(alunoVOS);

    }

    //listar pelo ID
    @GetMapping("/{idAluno}")
    public ResponseEntity <AlunoVO>findById(@PathVariable Integer idAluno){
        AlunoVO alunoVO = alunoDAO.obterPorId(idAluno);
        return ResponseEntity.ok(alunoVO);
    }

    @PostMapping("/inserir")
    //inserindo aluno
    public ResponseEntity<AlunoVO> insertNew(@RequestBody AlunoVO alunoVO){
        AlunoVO alunoVOInserido = alunoService.insertNewAluno(alunoVO);
        return ResponseEntity.ok(alunoVOInserido);
    }

    @PutMapping("/{idAluno}")
    public ResponseEntity<AlunoVO> update(@PathVariable Integer idAluno, @RequestBody AlunoVO alunoVOAlterado) {
        AlunoVO alunoVO = alunoService.update(idAluno, alunoVOAlterado);
        return ResponseEntity.ok().body(alunoVO);
    }

//    @DeleteMapping("/{idAluno}")
//    public ResponseEntity<Boolean> deleteById(@PathVariable Integer idAluno){
//        Boolean flag = alunoService.deleteById(idAluno);
//        return  ResponseEntity.ok().body(flag);
//    }
}