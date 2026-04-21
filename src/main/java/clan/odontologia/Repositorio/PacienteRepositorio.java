package clan.odontologia.Repositorio;

import clan.odontologia.Modelo.Odontologo;
import clan.odontologia.Modelo.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepositorio extends JpaRepository<Paciente, Long> {
  Optional<Paciente> findByidPaciente(Long idPaciente);

  Optional<Paciente> findByPersonaIdPersona(Long idPersona);
}
