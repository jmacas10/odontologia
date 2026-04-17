package clan.odontologia.Servicio;

import jakarta.transaction.Transactional;

public interface SecuenciaServicio {
    @Transactional
    String generarCodigo(String tipo, String prefijo);
}
