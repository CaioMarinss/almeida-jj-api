package br.com.almeidaPresenca.almeidaPresenca.services;

import br.com.almeidaPresenca.almeidaPresenca.dto.ResetSenhaDTO;
import br.com.almeidaPresenca.almeidaPresenca.infra.security.TokenService;
import br.com.almeidaPresenca.almeidaPresenca.models.Administrador;
import br.com.almeidaPresenca.almeidaPresenca.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  AdministradorService {

    @Autowired
    private  AdministradorRepository administradorRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;

    // listar todos os administradores
    public List<Administrador> findAll(){
        return administradorRepository.findAll();
    }

    // Buscar administrador por ID
    public Administrador findById(Integer idAdministrador) {
        return administradorRepository.findById(idAdministrador).orElse(null);
    }

    // Cadastrar novo administrador
    public Administrador insertNewAdministrador(Administrador administrador){
        return administradorRepository.save(administrador);
    }

    // Alterar cadastro de administrador
    public Administrador update(Integer idAdministrador, Administrador administradorAlterado){
        Administrador administradorAtual = findById(idAdministrador);

        if (administradorAtual == null) {
            return null;
        }

        administradorAtual.setNome(administradorAlterado.getNome());
        administradorAtual.setEmail(administradorAlterado.getEmail());
        administradorAtual.setSenha(administradorAlterado.getSenha());

        return administradorRepository.save(administradorAtual);
    }

    public void resetPassword(ResetSenhaDTO body) {
        String emailDoRequest = body.email();

        Administrador autenticado = (Administrador) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        String emailAutenticado = autenticado.getEmail();

        if (!emailDoRequest.equalsIgnoreCase(emailAutenticado)) {
            throw new RuntimeException("Token inválido para este e-mail.");
        }

        Administrador adm = administradorRepository.findByEmailIgnoreCase(emailDoRequest)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        adm.setSenha(passwordEncoder.encode(body.novaSenha()));
        administradorRepository.save(adm);
    }



    // Deletar administrador
    public boolean deleteById(Integer idAdministrador){
        Administrador administrador = findById(idAdministrador);
        if (administrador == null){
            return false;
        } else {
            administradorRepository.deleteById(idAdministrador);
            return true;
        }
    }
}
