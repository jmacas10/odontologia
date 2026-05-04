package clan.odontologia.Controlador;

import clan.odontologia.Dto.response.OdontologoResponseDTO;
import clan.odontologia.Servicio.OdontologoServicio;
import clan.odontologia.Servicio.PersonaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologo")
@RequiredArgsConstructor
public class OdontologoControlador {
    private final OdontologoServicio servicio;

    // CREAR
    @PostMapping
    public ResponseEntity<OdontologoResponseDTO> guardar(@RequestBody OdontologoResponseDTO request) {
        return ResponseEntity.ok(servicio.guardar(request));
    }

    // LISTAR
    @GetMapping
    public ResponseEntity<List<OdontologoResponseDTO>> listar() {
        return ResponseEntity.ok(servicio.listar());
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<OdontologoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(servicio.buscarPorId(id));
    }

    // EDITAR
    @PutMapping("/{id}")
    public ResponseEntity<OdontologoResponseDTO> editar(
            @PathVariable Long id,
            @RequestBody OdontologoResponseDTO request) {
        return ResponseEntity.ok(servicio.editar(id, request));
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
