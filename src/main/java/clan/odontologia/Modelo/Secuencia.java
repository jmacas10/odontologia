package clan.odontologia.Modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tSecuencias")
public class Secuencia {
  @Id
    private String tipo; // PERSONA, CITA, ODONTOLOGO

    private Long valor;
}
