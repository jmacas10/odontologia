package clan.odontologia.Servicio.ServicioIMPL;

import clan.odontologia.Dto.request.PersonaRequestDTO;
import clan.odontologia.Dto.response.PersonaResponseDTO;
import clan.odontologia.Modelo.Estado;
import clan.odontologia.Modelo.TipoIdentificacion;
import clan.odontologia.Servicio.PersonaServicio;

import clan.odontologia.Modelo.Persona;
import clan.odontologia.Repositorio.PersonaRepositorio;
import clan.odontologia.Servicio.SecuenciaServicio;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonaServicioImpl implements PersonaServicio {
private final PersonaRepositorio repositorio;
private final SecuenciaServicio secuenciaServicio;
    // GUARDAR
    @Override
    @Transactional(rollbackOn = Exception.class)
    public PersonaResponseDTO guardar(PersonaRequestDTO request) {

        //  Request → Entidad
        Persona persona = new Persona();
        persona.setCodigoPersona(request.getCodigoPersona());
        persona.setNombres(request.getNombres());
        persona.setApellidos(request.getApellidos());
        persona.setIdentificacion(request.getIdentificacion());
        //persona.setTipoIdentificacion(request.getTipoIdentificacion());
        persona.setTipoIdentificacion(TipoIdentificacion.valueOf(request.getTipoIdentificacion().toUpperCase()));
        persona.setFechaNacimiento(request.getFechaNacimiento());
        persona.setEstado(Estado.ACTIVO);

        persona.setFechaRegistro(LocalDateTime.now());

       // generar código
    persona.setCodigoPersona(
        secuenciaServicio.generarCodigo("PERSONA", "CP")
    );

    Persona guardado = repositorio.save(persona);
    guardado = repositorio.save(guardado);

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
       if(request.getNombres()!=null){
        persona.setNombres(request.getNombres());
       }
        if(request.getApellidos()!=null) {
            persona.setApellidos(request.getApellidos());
        }
        //ersona.setIdentificacion(request.getIdentificacion()); tampoco se actualiza la identificacion es identificador unico
       persona.setTipoIdentificacion(
    TipoIdentificacion.valueOf(request.getTipoIdentificacion().toUpperCase()));
        persona.setFechaNacimiento(request.getFechaNacimiento());
        if(request.getEstado()!=null) {
            persona.setEstado(request.getEstado());
        }
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
        dto.setTipoIdentificacion(p.getTipoIdentificacion().name());
        dto.setFechaNacimiento(p.getFechaNacimiento());
        dto.setEstado(p.getEstado());

        return dto;
    }
}
