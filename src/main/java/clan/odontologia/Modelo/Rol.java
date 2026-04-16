package clan.odontologia.Modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tRol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;

    private String nombre; // ADMIN, CLIENTE, ODONTOLOGO

}
