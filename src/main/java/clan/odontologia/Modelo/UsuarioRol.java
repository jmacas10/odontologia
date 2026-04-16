package clan.odontologia.Modelo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tUsuarioRol")
public class UsuarioRol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuarioRol;

        @ManyToOne
        @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

}
