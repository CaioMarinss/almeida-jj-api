package br.com.almeidaPresenca.almeidaPresenca.infra.security;



import br.com.almeidaPresenca.almeidaPresenca.dao.AlunoDAO;
import br.com.almeidaPresenca.almeidaPresenca.enums.SituacaoAtivoInativo;
import br.com.almeidaPresenca.almeidaPresenca.models.AlunoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomAdmDetailsService  implements UserDetailsService {

    @Autowired
    private AlunoDAO alunoDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AlunoVO alunoVO = this.alunoDAO.findByEmailIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (alunoVO.getSituacao().equals(SituacaoAtivoInativo.INATIVO.getValue())) {
            throw new RuntimeException("E-mail não verificado. Verifique seu e-mail antes de entrar.");
        }

        return new org.springframework.security.core.userdetails.User(
                alunoVO.getEmail(),
                alunoVO.getSenha(),
                new ArrayList<>()
        );
    }
}
