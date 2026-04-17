package clan.odontologia.Dto.request;

import clan.odontologia.Modelo.Estado;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonaRequestDTO {
    private String codigoPersona;
    private String nombres;
    private String apellidos;
    private String identificacion;
    private String tipoIdentificacion;
    private LocalDate fechaNacimiento;
    private Estado estado;

}
