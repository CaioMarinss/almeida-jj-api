package br.com.almeidaPresenca.almeidaPresenca.models;

import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AulaVO {

  private Integer idAula;

  private LocalTime horario;

  private LocalDateTime dthrRegistro;
}
