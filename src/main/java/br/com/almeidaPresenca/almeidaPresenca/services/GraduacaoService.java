package br.com.almeidaPresenca.almeidaPresenca.services;

import br.com.almeidaPresenca.almeidaPresenca.models.Aluno;
import br.com.almeidaPresenca.almeidaPresenca.models.Graduacao;
import br.com.almeidaPresenca.almeidaPresenca.repository.GraduacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraduacaoService {

    @Autowired
    private GraduacaoRepository graduacaoRepository;

    /*buscar todos alunos por graduação
    public List<Aluno> alunosPorGraduacao(Integer idGraduacao){
        Graduacao graduacao = graduacaoRepository.findById(idGraduacao)
                .orElseThrow(() -> new RuntimeException("Graduação não encontrada"));

        return graduacao.getAlunosPorGraduacao();
    }
    */
}
