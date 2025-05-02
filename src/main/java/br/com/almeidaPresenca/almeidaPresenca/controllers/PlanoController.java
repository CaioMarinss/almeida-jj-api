package br.com.almeidaPresenca.almeidaPresenca.controllers;

import br.com.almeidaPresenca.almeidaPresenca.models.Aluno;
import br.com.almeidaPresenca.almeidaPresenca.services.PlanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlanoController {

    @Autowired
    private PlanoService planoService;

    /*
    @GetMapping("planos/{idPlano}/alunos")
    public ResponseEntity<List<Aluno>> alunosPorPlano(@PathVariable Integer idPlano){


        List<Aluno> alunos = planoService.buscarAlunosPorPlano(idPlano);
        return ResponseEntity.ok(alunos);
    }

     */
}
