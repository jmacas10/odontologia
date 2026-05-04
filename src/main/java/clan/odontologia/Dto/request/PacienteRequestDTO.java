package clan.odontologia.Dto.request;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PacienteRequestDTO {
    private Long personaId;
    private String alergias;
    private String enfermedades;
    private String observaciones;
    private LocalDateTime fechaRegistro;



}
