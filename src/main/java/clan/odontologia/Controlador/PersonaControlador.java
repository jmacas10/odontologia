package clan.odontologia.Controlador;

import clan.odontologia.Servicio.PersonaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas")
@RequiredArgsConstructor
public class PersonaControlador {
    private final PersonaServicio servicio;
     //CREAR PERSONA
    @PostMapping
    public ResponseEntity<clan.odontologia.Dto.response.PersonaResponseDTO> guardar(@RequestBody clan.odontologia.Dto.request.PersonaRequestDTO request) {
        return ResponseEntity.ok(servicio.guardar(request));
    }

    // LISTAR PERSONAS
    @GetMapping
    public ResponseEntity<List<clan.odontologia.Dto.response.PersonaResponseDTO>> listar() {
        return ResponseEntity.ok(servicio.listar());
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<clan.odontologia.Dto.response.PersonaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(servicio.buscarPorId(id));
    }

    // ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<clan.odontologia.Dto.response.PersonaResponseDTO> editar(
            @PathVariable Long id,
            @RequestBody clan.odontologia.Dto.request.PersonaRequestDTO request) {
        return ResponseEntity.ok(servicio.editar(id, request));
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
