package clan.odontologia.DTO.response;


import lombok.Data;



@Data
public class ContactoResponseDTO {
    private Long idContacto;
    private Long personaId;
    private String telefono;
    private String email;
    private String direccion;

}
