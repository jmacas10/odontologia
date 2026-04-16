package clan.odontologia.Modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "tSesion")
public class Sesion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSesion;
        @ManyToOne
        @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String token;

    private LocalDate inicio;

    private LocalDate expiracion;

    private Boolean activa;




}
