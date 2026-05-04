package clan.odontologia.Modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tOdontologo")
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOdontologo;
    @OneToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;
   private String especialidad;
    private String registroProfesional;


}
