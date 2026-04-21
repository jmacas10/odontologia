package clan.odontologia.Repositorio;

import clan.odontologia.Modelo.Contacto;
import clan.odontologia.Modelo.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OdontologoRepositorio extends JpaRepository<Odontologo, Long> {
  Optional<Odontologo> findByidOdontologo(Long idOdontologo);

  Optional<Odontologo> findByPersonaIdPersona(Long idPersona);
}
