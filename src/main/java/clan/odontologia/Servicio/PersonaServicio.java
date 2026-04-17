package clan.odontologia.Servicio;

import clan.odontologia.Dto.request.PersonaRequestDTO;
import clan.odontologia.Dto.response.PersonaResponseDTO;

import java.util.List;

public interface PersonaServicio {
    // GUARDAR
    PersonaResponseDTO guardar(PersonaRequestDTO request);

    //  LISTAR
    List<PersonaResponseDTO> listar();

    //  BUSCAR POR ID
    PersonaResponseDTO buscarPorId(Long id);

    //  ELIMINAR
    void eliminar(Long id);

    PersonaResponseDTO editar(Long id, PersonaRequestDTO request);
}
