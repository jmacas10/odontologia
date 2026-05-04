package clan.odontologia.Servicio;

import clan.odontologia.Dto.response.OdontologoResponseDTO;

import java.util.List;

public interface OdontologoServicio {
    OdontologoResponseDTO guardar(OdontologoResponseDTO request);

    List<OdontologoResponseDTO> listar();

    // BUSCAR POR ID
    OdontologoResponseDTO buscarPorId(Long id);

    // EDITAR
    OdontologoResponseDTO editar(Long id, OdontologoResponseDTO request);

    // ELIMINAR
    void eliminar(Long id);
}
