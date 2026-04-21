package clan.odontologia.Servicio.ServicioIMPL;

import clan.odontologia.DTO.request.ContactoRequestDTO;
import clan.odontologia.DTO.response.ContactoResponseDTO;
import clan.odontologia.Dto.response.OdontologoResponseDTO;
import clan.odontologia.Modelo.Contacto;
import clan.odontologia.Modelo.Odontologo;
import clan.odontologia.Modelo.Persona;
import clan.odontologia.Repositorio.ContactoRepositorio;
import clan.odontologia.Repositorio.OdontologoRepositorio;
import clan.odontologia.Repositorio.PersonaRepositorio;
import clan.odontologia.Servicio.ContactoServicio;
import clan.odontologia.Servicio.OdontologoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class OdontologoServicioImpl implements OdontologoServicio {

    private final OdontologoRepositorio  odontologoRepositorio;
    private final PersonaRepositorio personaRepositorio;
   @Override
    public OdontologoResponseDTO guardar(OdontologoResponseDTO request) {

        // 1. Buscar persona por código
       Persona persona=personaRepositorio.findByIdPersona(request.getPersonaId())
               .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        // 2. Crear entidad Odontologo
        Odontologo odontologo = new Odontologo();
       odontologo.setPersona(persona);
       odontologo.setEspecialidad(request.getEspecialidad());


        // 3. Guardar
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
        Odontologo odontologo = odontologoRepositorio.findByidOdontologo(id)
                .orElseThrow(() -> new RuntimeException("Odontologo no encontrado"));

        return convertirAResponse(odontologo);
    }

    // EDITAR
    @Override
    public OdontologoResponseDTO editar(Long id, OdontologoResponseDTO request) {

        Contacto contacto = odontologoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Contacto no encontrado"));

        Persona persona = personaRepositorio.findByIdPersona(request.getPersonaId())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        contacto.setPersona(persona);
        contacto.setTelefono(request.getTelefono());
        contacto.setEmail(request.getEmail());
        contacto.setDireccion(request.getDireccion());

        Contacto actualizado = odontologoRepositorio.save(contacto);

        return convertirAResponse(actualizado);
    }
    // ELIMINAR
    @Override
    public void eliminar(Long id) {
        odontologoRepositorio.deleteById(id);
    }

    // MAPPER ENTITY -> DTO
    private OdontologoResponseDTO  convertirAResponse(Odontologo o) {

        OdontologoResponseDTO dto = new OdontologoResponseDTO();

        dto.setIdOdontologo(o.getIdOdontologo());
        dto.setEspecialidad(o.getEspecialidad());

        // relación
        dto.setPersonaId(
                c.getPersona() != null ? c.getPersona().getIdPersona() : null
        );

        return dto;
    }
}
