package clan.odontologia.Servicio.ServicioIMPL;

import clan.odontologia.Modelo.Secuencia;
import clan.odontologia.Repositorio.SecuenciaRepositorio;
import clan.odontologia.Servicio.SecuenciaServicio;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecuenciaServicioImpl implements SecuenciaServicio {
     private final SecuenciaRepositorio repositorio;

    @Override
    @Transactional
    public String generarCodigo(String tipo, String prefijo) {

        Secuencia secuencia = repositorio.obtenerPorTipoBloqueado(tipo);

        if (secuencia == null) {
            secuencia = new Secuencia();
            secuencia.setTipo(tipo);
            secuencia.setValor(0L);
        }

        secuencia.setValor(secuencia.getValor() + 1);

        repositorio.save(secuencia);

        return String.format(prefijo + "%06d", secuencia.getValor());
    }
}
