package clan.odontologia.Modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "tNotificacion")
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotificacion;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne
     @JoinColumn(name = "cita_id")
   private Cita cita;

    private String tipo;
    // EMAIL, WHATSAPP, SISTEMA

    private String titulo;

    private String mensaje;

    private String estado;
    // PENDIENTE, ENVIADO, FALLIDO

    private LocalDate fechaProgramada;

    private LocalDate fechaEnvio;

    private Boolean estadoNotificacion;




}
