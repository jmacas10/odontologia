package clan.odontologia.Dto.request;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PacienteRequestDTO {
    private Long personaId;
    private int estado;
    private LocalDateTime fechaActivacion;


}
