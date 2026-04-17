package clan.odontologia.Repositorio;

import clan.odontologia.Modelo.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonaRepositorio extends JpaRepository<Persona, Long> {
  Optional<Persona> findByCodigoPersona(String codigoPersona);

  Optional<Persona> findByIdentificacion(String identificacion);
  Optional<Persona> findByIdPersona(Long idPersona);
}
