package clan.odontologia.Dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class PacienteResponseDTO {
    private Long idPaciente;
    private Long personaId;
    private String alergias;
    private String enfermedades;
    private String observaciones;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaRegistro;


}
