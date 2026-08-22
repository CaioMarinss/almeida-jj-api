package br.com.almeidaPresenca.almeidaPresenca.infra.security;

import br.com.almeidaPresenca.almeidaPresenca.dao.AlunoDAO;
import br.com.almeidaPresenca.almeidaPresenca.models.AlunoVO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AlunoDAO alunoDAO;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("Authorization Header: " + request.getHeader("Authorization"));

        String path = request.getServletPath();

        if (path.equals("/auth/login") ||
                path.equals("/auth/register") ||
                path.equals("/auth/enviar-email-recuperacao") ||
                path.equals("/auth/enviar-email-verificacao")) {
            filterChain.doFilter(request, response);
            return;
        }


        String token = recoverToken(request);

        if (token != null) {
            String login = tokenService.validateToken(token);

            if (login != null) {
                AlunoVO alunoVO = alunoDAO.obterPorEmail(login);

                if (alunoVO != null) {
                    var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
                    var authentication = new UsernamePasswordAuthenticationToken(alunoVO, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }
        return null;
    }
}
