package clan.odontologia.Modelo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tContactos")
public class Contacto {
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContacto;

    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    private String telefono;
    private String email;
    private String direccion;

}
