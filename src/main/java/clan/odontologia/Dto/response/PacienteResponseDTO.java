package clan.odontologia.Dto.response;


import lombok.Data;

import java.time.LocalDateTime;


@Data
public class PacienteResponseDTO {
    private Long idPaciente;
    private Long personaId;
    private int estado;
    private LocalDateTime fechaActivacion;

}
