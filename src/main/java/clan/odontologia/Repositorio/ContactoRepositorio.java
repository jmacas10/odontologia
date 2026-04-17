package clan.odontologia.Repositorio;

import clan.odontologia.Modelo.Contacto;
import clan.odontologia.Modelo.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactoRepositorio extends JpaRepository<Contacto, Long> {
  Optional<Contacto> findByidContacto(Long idContacto);

  Optional<Contacto> findByPersonaIdPersona(Long idPersona);
}
