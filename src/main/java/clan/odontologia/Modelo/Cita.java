package clan.odontologia.Modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "tCita")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCita;
    @OneToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    @ManyToOne
     @JoinColumn(name = "odontologo_id")
   private Odontologo odontologo;
    private LocalDate fecha;

    private LocalTime hora;

    private String motivo;

    private String estado;
    // AGENDADA, CANCELADA, REAGENDADA, ATENDIDA

    private LocalDate fechaRegistro;



}
