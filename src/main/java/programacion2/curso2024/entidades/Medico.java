package programacion2.curso2024.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import programacion2.curso2024.enumeracion.ObraSocial;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medico {
    String nombre;
    Especialidad especialidad;
    boolean atiendeParticular;
    List<ObraSocial> obrasSociales;
    private int id;
    private Estado estado;


    public int getId() {
        return id;
    }

    public enum Estado {
        LIBRE, OCUPADO
    }

    public enum Especialidad {
        CLINICA,
        PEDIATRIA,
        TRAUMATOLOGIA,
        OFTALMOLOGIA,
        GINECOLOGIA,
        CARDIOLOGIA
    }
    public Medico(String nombre, Especialidad especialidad, boolean atiendeParticular ,int id) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.atiendeParticular = atiendeParticular;
        this.obrasSociales = obrasSociales;
        this.id = id;
        this.estado = Estado.LIBRE;
    }
}
