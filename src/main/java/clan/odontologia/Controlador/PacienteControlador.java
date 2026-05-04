package clan.odontologia.Controlador;

import clan.odontologia.Dto.response.PacienteResponseDTO;
import clan.odontologia.Servicio.PacienteServicio;
import clan.odontologia.Servicio.PersonaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
@RequiredArgsConstructor
public class PacienteControlador {
    private final PacienteServicio servicio;
    @PostMapping
    public ResponseEntity<PacienteResponseDTO> guardar(@RequestBody PacienteResponseDTO request) {
        return ResponseEntity.ok(servicio.guardar(request));
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> listar() {
        return ResponseEntity.ok(servicio.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(servicio.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> editar(
            @PathVariable Long id,
            @RequestBody PacienteResponseDTO request) {
        return ResponseEntity.ok(servicio.editar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
