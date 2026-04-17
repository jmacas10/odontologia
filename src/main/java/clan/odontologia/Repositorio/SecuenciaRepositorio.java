package clan.odontologia.Repositorio;

import clan.odontologia.Modelo.Secuencia;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SecuenciaRepositorio  extends JpaRepository<Secuencia, String> {
@Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM Secuencia s WHERE s.tipo = :tipo")
    Secuencia obtenerPorTipoBloqueado(@Param("tipo") String tipo);
}
