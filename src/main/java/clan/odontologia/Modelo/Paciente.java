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
    private String alergias;
    private String enfermedades;
    private String observaciones;
    private LocalDateTime fechaRegistro;
    @PrePersist
    public void prePersist() {
        this.fechaRegistro = LocalDateTime.now();
    }




}
