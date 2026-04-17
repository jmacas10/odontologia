package clan.odontologia.Servicio;

import clan.odontologia.DTO.request.ContactoRequestDTO;
import clan.odontologia.DTO.response.ContactoResponseDTO;

import java.util.List;

public interface ContactoServicio {
    ContactoResponseDTO guardar(ContactoRequestDTO request);

    List<ContactoResponseDTO> listar();

    // BUSCAR POR ID
    ContactoResponseDTO buscarPorId(Long id);

    // EDITAR
    ContactoResponseDTO editar(Long id, ContactoRequestDTO request);

    // ELIMINAR
    void eliminar(Long id);
}
