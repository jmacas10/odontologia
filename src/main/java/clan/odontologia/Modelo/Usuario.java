package clan.odontologia.Modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tUsuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @OneToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @Column(unique = true)
    private String username; // cédula o email

    private String password;

    private int estado; // ACTIVO / BLOQUEADO

    private int intentosFallidos;

    private LocalDateTime ultimoAcceso;

}
