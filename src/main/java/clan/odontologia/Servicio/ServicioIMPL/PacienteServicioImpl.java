package clan.odontologia.Servicio.ServicioIMPL;

import clan.odontologia.Dto.response.PacienteResponseDTO;
import clan.odontologia.Modelo.Paciente;
import clan.odontologia.Modelo.Persona;
import clan.odontologia.Repositorio.PacienteRepositorio;
import clan.odontologia.Repositorio.PersonaRepositorio;
import clan.odontologia.Servicio.PacienteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteServicioImpl  implements PacienteServicio {
    private final PacienteRepositorio pacienteRepositorio;
    private final PersonaRepositorio personaRepositorio;
    // GUARDAR
    @Override
    public PacienteResponseDTO guardar(PacienteResponseDTO request) {

        Persona persona = personaRepositorio.findByIdPersona(request.getPersonaId())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        Paciente paciente = new Paciente();
        paciente.setPersona(persona);
        paciente.setAlergias(request.getAlergias());
        paciente.setEnfermedades(request.getEnfermedades());
        paciente.setObservaciones(request.getObservaciones());

        Paciente guardado = pacienteRepositorio.save(paciente);

        return convertirAResponse(guardado);
    }

    // LISTAR
    @Override
    public List<PacienteResponseDTO> listar() {
        return pacienteRepositorio.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    // BUSCAR POR ID
    @Override
    public PacienteResponseDTO buscarPorId(Long id) {
        Paciente paciente = pacienteRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        return convertirAResponse(paciente);
    }

    // EDITAR
    @Override
    public PacienteResponseDTO editar(Long id, PacienteResponseDTO request) {

        Paciente paciente = pacienteRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        Persona persona = personaRepositorio.findByIdPersona(request.getPersonaId())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        paciente.setPersona(persona);
        paciente.setAlergias(request.getAlergias());
        paciente.setEnfermedades(request.getEnfermedades());
        paciente.setObservaciones(request.getObservaciones());

        Paciente actualizado = pacienteRepositorio.save(paciente);

        return convertirAResponse(actualizado);
    }

    // ELIMINAR
    @Override
    public void eliminar(Long id) {
        pacienteRepositorio.deleteById(id);
    }

    // MAPPER ENTITY -> DTO
    private PacienteResponseDTO convertirAResponse(Paciente p) {

        PacienteResponseDTO dto = new PacienteResponseDTO();

        dto.setIdPaciente(p.getIdPaciente());
        dto.setAlergias(p.getAlergias());
        dto.setEnfermedades(p.getEnfermedades());
        dto.setObservaciones(p.getObservaciones());
        dto.setFechaRegistro(p.getFechaRegistro());
        dto.setPersonaId(
                p.getPersona() != null ? p.getPersona().getIdPersona() : null
        );

        return dto;
    }
}