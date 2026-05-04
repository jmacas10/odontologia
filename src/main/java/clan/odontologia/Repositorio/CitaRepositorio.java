package clan.odontologia.Repositorio;

import clan.odontologia.Modelo.Cita;
import clan.odontologia.Modelo.EstadoCita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface CitaRepositorio extends JpaRepository<Cita, Long> {

    List<Cita> findByFechaAndEstado(LocalDate fecha, EstadoCita estado);

    List<Cita> findByFechaAndEstadoAndOdontologoIdOdontologo(
            LocalDate fecha, EstadoCita estado, Long id
    );

    Cita findByFechaAndHora(LocalDate fecha, LocalTime hora);
}
