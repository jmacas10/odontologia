package clan.odontologia.Dto.response;

import clan.odontologia.Modelo.Estado;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonaResponseDTO {
    private Long idPersona;
    private String codigoPersona;
    private String nombres;
    private String apellidos;
    private String identificacion;
    private String tipoIdentificacion;
    private LocalDate fechaNacimiento;
    private Estado estado;
}
