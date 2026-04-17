package clan.odontologia.Servicio.ServicioImpl;

import clan.odontologia.Dto.request.PersonaRequestDTO;
import clan.odontologia.Dto.response.PersonaResponseDTO;
import clan.odontologia.Servicio.PersonaServicio;

import clan.odontologia.Modelo.Persona;
import clan.odontologia.Repositorio.PersonaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonaServicionImpl  implements PersonaServicio {
private final PersonaRepositorio repositorio;

    // GUARDAR
    @Override
    public PersonaResponseDTO guardar(PersonaRequestDTO request) {

        //  Request → Entidad
        Persona persona = new Persona();
        persona.setCodigoPersona(request.getCodigoPersona());
        persona.setNombres(request.getNombres());
        persona.setApellidos(request.getApellidos());
        persona.setIdentificacion(request.getIdentificacion());
        persona.setTipoIdentificacion(request.getTipoIdentificacion());
        persona.setFechaNacimiento(request.getFechaNacimiento());
        persona.setEstado(request.getEstado());

        persona.setFechaRegistro(LocalDateTime.now());

        // Guardar en BD
        Persona guardado = repositorio.save(persona);

        //  Entidad → Response
        return convertirAResponse(guardado);
    }

    //  LISTAR
    @Override
    public List<PersonaResponseDTO> listar() {
        return repositorio.findAll()
                .stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    //  BUSCAR POR ID
    @Override
    public PersonaResponseDTO buscarPorId(Long id) {
        Persona persona = repositorio.findById(id).orElseThrow();
        return convertirAResponse(persona);
    }

    //  ELIMINAR
    @Override
    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }

    @Override
    public PersonaResponseDTO editar(Long id, PersonaRequestDTO request) {

        //  Buscar en BD
        // buscar por identificación
        Persona persona = repositorio.findByIdentificacion(request.getIdentificacion())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        // Actualizar datos
       // persona.setCodigoPersona(request.getCodigoPersona()); no se actualiza el codigo es identificador unico
        persona.setNombres(request.getNombres());
        persona.setApellidos(request.getApellidos());
        //ersona.setIdentificacion(request.getIdentificacion()); tampoco se actualiza la identificacion es identificador unico
        persona.setTipoIdentificacion(request.getTipoIdentificacion());
        persona.setFechaNacimiento(request.getFechaNacimiento());
        persona.setEstado(request.getEstado());

        persona.setFechaModificacion(LocalDateTime.now());

        //  Guardar
        Persona actualizado = repositorio.save(persona);

        //  Convertir a DTO
        return convertirAResponse(actualizado);
    }

    // MÉTODO CLAVE: Entidad → ResponseDTO
    private PersonaResponseDTO convertirAResponse(Persona p) {

        PersonaResponseDTO dto = new PersonaResponseDTO();

        dto.setIdPersona(p.getIdPersona());
        dto.setCodigoPersona(p.getCodigoPersona());
        dto.setNombres(p.getNombres());
        dto.setApellidos(p.getApellidos());
        dto.setIdentificacion(p.getIdentificacion());
        dto.setTipoIdentificacion(p.getTipoIdentificacion());
        dto.setFechaNacimiento(p.getFechaNacimiento());
        dto.setEstado(p.getEstado());

        return dto;
    }
}
