package clan.odontologia.Servicio.ServicioIMPL;

import clan.odontologia.DTO.request.ContactoRequestDTO;
import clan.odontologia.DTO.response.ContactoResponseDTO;

import clan.odontologia.Modelo.Contacto;

import clan.odontologia.Modelo.Persona;

import clan.odontologia.Repositorio.ContactoRepositorio;
import clan.odontologia.Repositorio.PersonaRepositorio;
import clan.odontologia.Servicio.ContactoServicio;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class ContactoServicioImpl implements ContactoServicio {

    private final ContactoRepositorio contactoRepositorio;
    private final PersonaRepositorio personaRepositorio;
   @Override
    public ContactoResponseDTO guardar(ContactoRequestDTO request) {

        // 1. Buscar persona por código
       Persona persona=personaRepositorio.findByIdPersona(request.getPersonaId())
               .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        // 2. Crear entidad contacto
        Contacto contacto = new Contacto();
        contacto.setPersona(persona);
        contacto.setTelefono(request.getTelefono());
        contacto.setEmail(request.getEmail());
        contacto.setDireccion(request.getDireccion());

        // 3. Guardar
        Contacto guardado = contactoRepositorio.save(contacto);

        return convertirAResponse(guardado);
    }


    // LISTAR

    @Override
    public List<ContactoResponseDTO> listar() {
        return contactoRepositorio.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    // BUSCAR POR ID
    @Override
    public ContactoResponseDTO buscarPorId(Long id) {
        Contacto contacto = contactoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Contacto no encontrado"));

        return convertirAResponse(contacto);
    }

    // EDITAR
    @Override
    public ContactoResponseDTO editar(Long id, ContactoRequestDTO request) {

        Contacto contacto = contactoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Contacto no encontrado"));

        Persona persona = personaRepositorio.findByIdPersona(request.getPersonaId())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        contacto.setPersona(persona);
        contacto.setTelefono(request.getTelefono());
        contacto.setEmail(request.getEmail());
        contacto.setDireccion(request.getDireccion());

        Contacto actualizado = contactoRepositorio.save(contacto);

        return convertirAResponse(actualizado);
    }
    // ELIMINAR
    @Override
    public void eliminar(Long id) {
        contactoRepositorio.deleteById(id);
    }

    // MAPPER ENTITY -> DTO
    private ContactoResponseDTO convertirAResponse(Contacto c) {

        ContactoResponseDTO dto = new ContactoResponseDTO();

        dto.setIdContacto(c.getIdContacto());
        dto.setTelefono(c.getTelefono());
        dto.setEmail(c.getEmail());
        dto.setDireccion(c.getDireccion());

        // relación
        dto.setPersonaId(
                c.getPersona() != null ? c.getPersona().getIdPersona() : null
        );

        return dto;
    }
}
