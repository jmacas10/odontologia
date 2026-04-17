package clan.odontologia.Config;

import clan.odontologia.Modelo.Secuencia;
import clan.odontologia.Repositorio.SecuenciaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final SecuenciaRepositorio repositorio;
    @Override
    public void run(String... args) throws Exception {

        if (!repositorio.existsById("PERSONA")) {
            Secuencia sec = new Secuencia();
            sec.setTipo("PERSONA");
            sec.setValor(0L);
            repositorio.save(sec);
        }

        if (!repositorio.existsById("CITA")) {
            Secuencia sec = new Secuencia();
            sec.setTipo("CITA");
            sec.setValor(0L);
            repositorio.save(sec);
        }

        if (!repositorio.existsById("ODONTOLOGO")) {
            Secuencia sec = new Secuencia();
            sec.setTipo("ODONTOLOGO");
            sec.setValor(0L);
            repositorio.save(sec);
        }
    }
}
