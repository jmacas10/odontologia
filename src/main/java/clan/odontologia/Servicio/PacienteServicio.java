package clan.odontologia.Servicio;

import clan.odontologia.Dto.response.PacienteResponseDTO;

import java.util.List;

public interface PacienteServicio {
    // GUARDAR
    PacienteResponseDTO guardar(PacienteResponseDTO request);

    // LISTAR
    List<PacienteResponseDTO> listar();

    // BUSCAR POR ID
    PacienteResponseDTO buscarPorId(Long id);

    // EDITAR
    PacienteResponseDTO editar(Long id, PacienteResponseDTO request);

    // ELIMINAR
    void eliminar(Long id);
}
