package br.com.almeidaPresenca.almeidaPresenca.infra.security;



import br.com.almeidaPresenca.almeidaPresenca.SituacaoAtivoInativo;
import br.com.almeidaPresenca.almeidaPresenca.models.AdministradorVO;
import br.com.almeidaPresenca.almeidaPresenca.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomAdmDetailsService  implements UserDetailsService {

    @Autowired
    private AdministradorRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AdministradorVO administradorVO = this.repository.findByEmailIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (administradorVO.getSituacao().equals(SituacaoAtivoInativo.INATIVO.getValue())) {
            throw new RuntimeException("E-mail não verificado. Verifique seu e-mail antes de entrar.");
        }

        return new org.springframework.security.core.userdetails.User(
                administradorVO.getEmail(),
                administradorVO.getSenha(),
                new ArrayList<>()
        );
    }
}
