package clan.odontologia.Modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tPaciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaciente;
    @OneToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    private int estado;

    private LocalDateTime fechaActivacion;



}
