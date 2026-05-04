package clan.odontologia.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
    private int status;
    private String mensaje;
    private Object data;
}
