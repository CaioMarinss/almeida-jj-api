package br.com.almeidaPresenca.almeidaPresenca.models;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PresencaVO {

  private Integer idPresenca;

  private Integer idAluno;

  private Integer idAula;

  private LocalDateTime dthrRegistro;
}
