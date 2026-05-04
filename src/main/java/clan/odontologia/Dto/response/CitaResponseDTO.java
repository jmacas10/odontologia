package clan.odontologia.Dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;


@Data
public class CitaResponseDTO {
    private Long idCita;

    private Long pacienteId;

    private Long odontologoId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime hora;

    private String motivo;

    private String estado;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRegistro;
}
