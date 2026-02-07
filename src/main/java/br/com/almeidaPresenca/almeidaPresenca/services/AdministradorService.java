package br.com.almeidaPresenca.almeidaPresenca.services;

import br.com.almeidaPresenca.almeidaPresenca.SituacaoAtivoInativo;
import br.com.almeidaPresenca.almeidaPresenca.dto.ResetSenhaDTO;
import br.com.almeidaPresenca.almeidaPresenca.infra.security.TokenService;
import br.com.almeidaPresenca.almeidaPresenca.models.AdministradorVO;
import br.com.almeidaPresenca.almeidaPresenca.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class  AdministradorService {

    @Autowired
    private  AdministradorRepository administradorRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private EmailService emailService;

    // listar todos os administradores
    public List<AdministradorVO> findAll(){
        return administradorRepository.findAll();
    }

    // Buscar administrador por ID
    public AdministradorVO findById(Integer idAdministrador) {
        return administradorRepository.findById(idAdministrador).orElse(null);
    }

    public AdministradorVO save(AdministradorVO administradorVO) {
        return administradorRepository.save(administradorVO);
    }

    // Cadastrar novo administrador
    public AdministradorVO insertNewAdministrador(AdministradorVO administradorVO) {
        administradorVO.setSituacao(SituacaoAtivoInativo.INATIVO.getValue()); // obriga que verifique antes de usar
        AdministradorVO administradorVOSalvo = administradorRepository.save(administradorVO);

        emailService.sendEmailVerification(administradorVOSalvo);
        return administradorVOSalvo;
    }

    // Alterar cadastro de administrador
    public AdministradorVO update(Integer idAdministrador, AdministradorVO administradorVOAlterado){
        AdministradorVO administradorVOAtual = findById(idAdministrador);

        if (administradorVOAtual == null) {
            return null;
        }

        administradorVOAtual.setNome(administradorVOAlterado.getNome());
        administradorVOAtual.setEmail(administradorVOAlterado.getEmail());
        administradorVOAtual.setSenha(administradorVOAlterado.getSenha());

        return administradorRepository.save(administradorVOAtual);
    }

    public void resetPassword(ResetSenhaDTO body) {
        String emailDoRequest = body.email();

        AdministradorVO autenticado = (AdministradorVO) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        String emailAutenticado = autenticado.getEmail();

        if (!emailDoRequest.equalsIgnoreCase(emailAutenticado)) {
            throw new RuntimeException("Token inválido para este e-mail.");
        }

        AdministradorVO adm = administradorRepository.findByEmailIgnoreCase(emailDoRequest)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        adm.setSenha(passwordEncoder.encode(body.novaSenha()));
        administradorRepository.save(adm);
    }

    //achar por email sem se preocupar com caps
    public Optional<AdministradorVO> findByEmailIgnoreCase(String email) {
        return administradorRepository.findByEmailIgnoreCase(email);
    }

    // Deletar administrador
    public boolean deleteById(Integer idAdministrador){
        AdministradorVO administradorVO = findById(idAdministrador);
        if (administradorVO == null){
            return false;
        } else {
            administradorRepository.deleteById(idAdministrador);
            return true;
        }
    }
}
