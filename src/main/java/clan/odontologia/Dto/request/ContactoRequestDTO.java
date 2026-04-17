package clan.odontologia.DTO.request;


import lombok.Data;

import java.time.LocalDate;

@Data
public class ContactoRequestDTO {
    private Long personaId;
    private String telefono;
    private String email;
    private String direccion;


}
