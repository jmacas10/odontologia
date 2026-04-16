package clan.odontologia.Modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tPersona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;

    @Column(unique = true, nullable = false)
    private String codigoPersona; // cpersona

    private String nombres;
    private String apellidos;

    @Column(unique = true)
    private String identificacion;

    private String tipoIdentificacion;

    private LocalDate fechaNacimiento;

    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaModificacion;

    private String estado; // ACTIVO / INACTIVO

}
