package br.com.almeidaPresenca.almeidaPresenca.infra.security;

import br.com.almeidaPresenca.almeidaPresenca.models.Administrador;
import br.com.almeidaPresenca.almeidaPresenca.models.Aluno;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    // @Value vai buscar o valor da chave no arquivo application.properties
    @Value("${api.security.token.secret}")
    private String secret;


    //metodo que vai definir o tempo q dura o token
    private Instant expirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.ofHours(-3));  // UTC-3 (Brasília)
    }

    // GERAR O TOKEN
    public String generateToken(Administrador administrador) {

        try {
            // Algoritmo HMAC256 para criptografar o token
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("almeidaPresenca") // Quem está emitindo o token
                    .withSubject(administrador.getEmail())  // O assunto do token (pode ser o nome do aluno)
                    .withIssuedAt(new Date())      // Data de emissão
                    .withExpiresAt(this.expirationDate()) // Expira em 2 horas como defini no metodo la em baixo

                    .sign(algorithm);  // Gera o token com o algoritmo definido

            return token;
        } catch (JWTCreationException e) {
            // se der erro na criação do JWT, lança uma exception
            throw new RuntimeException("Error while generating the token", e);
        }
    }

    public  String validateToken(String token){

        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("almeidaPresenca")
                    .build()
                    .verify(token)
                    .getSubject(); //pega de volta o valor q salvamos la em cima

        } catch (JWTVerificationException e) {
            return null; //basta saber se foi autenticado ou não
        }
    }

}
