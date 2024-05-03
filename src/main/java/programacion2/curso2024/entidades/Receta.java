package programacion2.curso2024.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receta {
    private int id;
    private int idMedico;
    private List<Medicamento> medicamentos;


}
