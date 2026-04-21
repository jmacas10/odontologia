package clan.odontologia.Controlador;

import clan.odontologia.Servicio.ContactoServicio;
import clan.odontologia.Servicio.PersonaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contactos")
@RequiredArgsConstructor
public class ContactoControlador {
    private final ContactoServicio servicio;
     //CREAR Contacto
    @PostMapping
    public ResponseEntity<clan.odontologia.DTO.response.ContactoResponseDTO> guardar(@RequestBody clan.odontologia.DTO.request.ContactoRequestDTO request) {
        return ResponseEntity.ok(servicio.guardar(request));
    }

    // LISTAR contactos
    @GetMapping
    public ResponseEntity<List<clan.odontologia.DTO.response.ContactoResponseDTO>   > listar() {
        return ResponseEntity.ok(servicio.listar());
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<clan.odontologia.DTO.response.ContactoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(servicio.buscarPorId(id));
    }

    // ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<clan.odontologia.DTO.response.ContactoResponseDTO> editar(
            @PathVariable Long id,
            @RequestBody clan.odontologia.DTO.response.ContactoResponseDTO request) {
        return ResponseEntity.ok(servicio.editar(id, request));
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
