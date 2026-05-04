package clan.odontologia.Servicio.ServicioIMPL;

import clan.odontologia.Dto.response.OdontologoResponseDTO;
import clan.odontologia.Modelo.Contacto;
import clan.odontologia.Modelo.Odontologo;
import clan.odontologia.Modelo.Persona;
import clan.odontologia.Repositorio.OdontologoRepositorio;
import clan.odontologia.Repositorio.PersonaRepositorio;
import clan.odontologia.Servicio.OdontologoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class OdontologoServicioImpl implements OdontologoServicio {

    private final OdontologoRepositorio  odontologoRepositorio;
    private final PersonaRepositorio personaRepositorio;
    // GUARDAR
    @Override
    public OdontologoResponseDTO guardar(OdontologoResponseDTO request) {

        Persona persona = personaRepositorio.findByIdPersona(request.getPersonaId())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        Odontologo odontologo = new Odontologo();
        odontologo.setPersona(persona);
        odontologo.setEspecialidad(request.getEspecialidad());
        odontologo.setRegistroProfesional(request.getRegistroProfesional());

        Odontologo guardado = odontologoRepositorio.save(odontologo);

        return convertirAResponse(guardado);
    }

    // LISTAR
    @Override
    public List<OdontologoResponseDTO> listar() {
        return odontologoRepositorio.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    // BUSCAR POR ID
    @Override
    public OdontologoResponseDTO buscarPorId(Long id) {
        Odontologo odontologo = odontologoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Odontologo no encontrado"));

        return convertirAResponse(odontologo);
    }

    // EDITAR
    @Override
    public OdontologoResponseDTO editar(Long id, OdontologoResponseDTO request) {

        Odontologo odontologo = odontologoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Odontologo no encontrado"));

        Persona persona = personaRepositorio.findByIdPersona(request.getPersonaId())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        odontologo.setPersona(persona);
        odontologo.setEspecialidad(request.getEspecialidad());
        odontologo.setRegistroProfesional(request.getRegistroProfesional());

        Odontologo actualizado = odontologoRepositorio.save(odontologo);

        return convertirAResponse(actualizado);
    }

    // ELIMINAR
    @Override
    public void eliminar(Long id) {
        odontologoRepositorio.deleteById(id);
    }

    // MAPPER ENTITY -> DTO
    private OdontologoResponseDTO convertirAResponse(Odontologo o) {

        OdontologoResponseDTO dto = new OdontologoResponseDTO();

        dto.setIdOdontologo(o.getIdOdontologo());
        dto.setEspecialidad(o.getEspecialidad());
        dto.setRegistroProfesional(o.getRegistroProfesional());

        dto.setPersonaId(
                o.getPersona() != null ? o.getPersona().getIdPersona() : null
        );

        return dto;
    }
}