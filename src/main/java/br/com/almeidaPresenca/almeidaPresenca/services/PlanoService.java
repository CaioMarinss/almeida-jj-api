package br.com.almeidaPresenca.almeidaPresenca.services;

import br.com.almeidaPresenca.almeidaPresenca.models.Aluno;
import br.com.almeidaPresenca.almeidaPresenca.models.Plano;
import br.com.almeidaPresenca.almeidaPresenca.repository.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanoService {

    @Autowired
    private PlanoRepository planoRepository;

    /*
    //buscar planos
    public List<Aluno> buscarAlunosPorPlano(Integer idPlano){
        Plano plano = planoRepository.findById(idPlano).orElseThrow(() -> new RuntimeException("Plano não encontrado"));

        return plano.getAlunosPorPlano();
    }
    */
}
