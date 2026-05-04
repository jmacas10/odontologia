package clan.odontologia.Controlador;

import clan.odontologia.Dto.request.CitaRequestDTO;
import clan.odontologia.Dto.response.ApiResponse;
import clan.odontologia.Dto.response.CitaResponseDTO;
import clan.odontologia.Servicio.CitaServicio;
import clan.odontologia.Servicio.ContactoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cita")
@RequiredArgsConstructor
public class CitaControlador {
    private final CitaServicio servicio;

    // ✅ GENERAR AGENDA
    @PostMapping("/generar")
    public ResponseEntity<ApiResponse> generarAgenda(
            @RequestParam String fecha,
            @RequestParam Long odontologoId) {

        servicio.generarAgenda(fecha, odontologoId);

        return ResponseEntity.ok(
                new ApiResponse(200, "Agenda generada correctamente", null)
        );
    }

    // ✅ DISPONIBLES
    @GetMapping("/disponibles")
    public ResponseEntity<ApiResponse> disponibles(
            @RequestParam String fecha,
            @RequestParam Long odontologoId) {

        var data = servicio.obtenerDisponibles(fecha, odontologoId);

        return ResponseEntity.ok(
                new ApiResponse(200, "Consulta exitosa", data)
        );
    }

    // ✅ AGENDAR
    @PostMapping("/agendar")
    public ResponseEntity<ApiResponse> agendar(@RequestBody CitaRequestDTO request) {
        servicio.agendar(request);
        return ResponseEntity.status(201)
                .body(new ApiResponse(201, "Cita agendada correctamente", null));
    }

    // ✅ CANCELAR
    @PostMapping("/cancelar")
    public ResponseEntity<ApiResponse> cancelar(
            @RequestBody CitaRequestDTO request) {

        servicio.cancelar(request);

        return ResponseEntity.ok(
                new ApiResponse(200, "Cita cancelada correctamente", null)
        );
    }

    // ✅ REAGENDAR
    @PostMapping("/reagendar")
    public ResponseEntity<ApiResponse> reagendar(
            @RequestParam String fecha) {

        servicio.reagendarDia(fecha);

        return ResponseEntity.ok(
                new ApiResponse(200, "Citas reagendadas correctamente", null)
        );
    }
}