package clan.odontologia.Servicio;

import clan.odontologia.DTO.request.ContactoRequestDTO;
import clan.odontologia.DTO.response.ContactoResponseDTO;
import clan.odontologia.Dto.request.CitaRequestDTO;
import clan.odontologia.Dto.response.CitaResponseDTO;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CitaServicio {


    //  GENERAR AGENDA (09:00 - 18:00)
    void generarAgenda(String fecha, Long odontologoId);

    //  OBTENER DISPONIBLES
    List<CitaResponseDTO> obtenerDisponibles(String fecha, Long odontologoId);

    // AGENDAR
    void agendar(CitaRequestDTO request);

    // CANCELAR
    void cancelar(CitaRequestDTO request);

    //  REAGENDAR MASIVO (día siguiente misma hora)
    @Transactional
    void reagendarDia(String fecha);
}