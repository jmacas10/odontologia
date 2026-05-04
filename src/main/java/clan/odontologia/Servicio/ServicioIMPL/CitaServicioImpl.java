package clan.odontologia.Servicio.ServicioIMPL;

import clan.odontologia.Dto.request.CitaRequestDTO;
import clan.odontologia.Dto.response.CitaResponseDTO;
import clan.odontologia.Modelo.*;
import clan.odontologia.Repositorio.*;
import clan.odontologia.Servicio.CitaServicio;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CitaServicioImpl implements CitaServicio {

    private final CitaRepositorio citaRepositorio;
    private final PacienteRepositorio  pacienteRepositorio;
    private final OdontologoRepositorio odontologoRepositorio;

    //  GENERAR AGENDA (09:00 - 18:00)
    @Override
    public void generarAgenda(String fecha, Long odontologoId) {

        LocalDate f = LocalDate.parse(fecha);

        Odontologo odontologo = odontologoRepositorio.findById(odontologoId)
                .orElseThrow(() -> new RuntimeException("Odontólogo no encontrado"));

        for (int hora = 9; hora < 19; hora++) {

            Cita cita = new Cita();
            cita.setFecha(f);
            cita.setHora(LocalTime.of(hora, 0));
            cita.setEstado(EstadoCita.DISPONIBLE);
            cita.setOdontologo(odontologo);

            citaRepositorio.save(cita);
        }
    }

    //  OBTENER DISPONIBLES
    @Override
    public List<CitaResponseDTO> obtenerDisponibles(String fecha, Long odontologoId) {

        LocalDate f = LocalDate.parse(fecha);

        return citaRepositorio
                .findByFechaAndEstadoAndOdontologoIdOdontologo(
                        f, EstadoCita.DISPONIBLE, odontologoId
                )
                .stream()
                .map(this::convertir)
                .toList();
    }

    // AGENDAR
    @Override
    public void agendar(CitaRequestDTO request) {

        Cita cita = citaRepositorio.findById(request.getCitaId())
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        if (cita.getEstado() != EstadoCita.DISPONIBLE) {
            throw new RuntimeException("La cita no está disponible");
        }

        Paciente paciente = pacienteRepositorio
                .findByPersonaIdentificacion(request.getCedula())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        if (paciente == null) {
            throw new RuntimeException("Paciente no registrado");
        }

        cita.setPaciente(paciente);
        cita.setMotivo(request.getMotivo());
        cita.setEstado(EstadoCita.AGENDADA);

        citaRepositorio.save(cita);
    }

    // CANCELAR
    @Override
    public void cancelar(CitaRequestDTO request) {

        Cita cita = citaRepositorio.findById(request.getCitaId())
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        if (cita.getPaciente() == null) {
            throw new RuntimeException("La cita no tiene paciente");
        }

        if (!cita.getPaciente().getPersona().getIdentificacion()
                .equals(request.getCedula())) {

            throw new RuntimeException("No autorizado para cancelar esta cita");
        }

        cita.setPaciente(null);
        cita.setMotivo(null);
        cita.setEstado(EstadoCita.DISPONIBLE);

        citaRepositorio.save(cita);
    }

    //  REAGENDAR MASIVO (día siguiente misma hora)
    @Override
    @Transactional
    public void reagendarDia(String fecha) {

        LocalDate fechaOriginal = LocalDate.parse(fecha);
        LocalDate nuevaFecha = fechaOriginal.plusDays(1);

        List<Cita> citas = citaRepositorio
                .findByFechaAndEstado(fechaOriginal, EstadoCita.AGENDADA);

        for (Cita cita : citas) {

            Cita nueva = citaRepositorio
                    .findByFechaAndHora(nuevaFecha, cita.getHora());

            if (nueva == null) {
                throw new RuntimeException("No existe horario " + cita.getHora());
            }

            if (nueva.getEstado() != EstadoCita.DISPONIBLE) {
                throw new RuntimeException("Conflicto en hora " + cita.getHora());
            }

            // mover paciente
            nueva.setPaciente(cita.getPaciente());
            nueva.setMotivo(cita.getMotivo());
            nueva.setEstado(EstadoCita.AGENDADA);

            // liberar anterior
            cita.setPaciente(null);
            cita.setMotivo(null);
            cita.setEstado(EstadoCita.DISPONIBLE);

            citaRepositorio.save(nueva);
            citaRepositorio.save(cita);
        }
    }

    // MAPPER
    private CitaResponseDTO convertir(Cita c) {

        CitaResponseDTO dto = new CitaResponseDTO();

        dto.setIdCita(c.getIdCita());

        dto.setPacienteId(
                c.getPaciente() != null ? c.getPaciente().getIdPaciente() : null
        );

        dto.setOdontologoId(
                c.getOdontologo() != null ? c.getOdontologo().getIdOdontologo() : null
        );

        dto.setFecha(c.getFecha());
        dto.setHora(c.getHora());
        dto.setMotivo(c.getMotivo());

        dto.setEstado(c.getEstado().name());

        dto.setFechaRegistro(c.getFechaRegistro());

        return dto;
    }
}