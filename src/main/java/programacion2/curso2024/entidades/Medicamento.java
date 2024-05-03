package programacion2.curso2024.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medicamento {
    private String nombre;
    private String dosis;
    private String instrucciones;

    public static List<Medicamento> generarMedicamentos(){
        List<Medicamento> medicamentos = new ArrayList<>();
        medicamentos.add(new Medicamento("Ibuprofeno", "1 comprimido", "Cada 8 horas"));
        medicamentos.add(new Medicamento("Paracetamol", "1 comprimido", "Cada 6 horas"));
        medicamentos.add(new Medicamento("Amoxicilina", "1 comprimido", "Cada 12 horas"));
        medicamentos.add(new Medicamento("Diclofenac", "1 comprimido", "Cada 12 horas"));
        medicamentos.add(new Medicamento("Omeprazol", "1 comprimido", "Cada 24 horas"));
        medicamentos.add(new Medicamento("Loratadina", "1 comprimido", "Cada 24 horas"));
        medicamentos.add(new Medicamento("Diazepam", "1 comprimido", "Cada 12 horas"));
        medicamentos.add(new Medicamento("Clonazepam", "1 comprimido", "Cada 12 horas"));
        medicamentos.add(new Medicamento("Cetirizina", "1 comprimido", "Cada 24 horas"));

        return medicamentos;




    }

}
