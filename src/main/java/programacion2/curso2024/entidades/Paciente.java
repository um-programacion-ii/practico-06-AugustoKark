package programacion2.curso2024.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import programacion2.curso2024.dao.PacienteDao;
import programacion2.curso2024.enumeracion.ObraSocial;
import programacion2.curso2024.services.ClinicaService;

import java.sql.SQLOutput;
import java.util.List;
import java.util.concurrent.Callable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente implements Callable {

    private String nombre;
    private String apellido;
    private ObraSocial obraSocial;
    private List<Receta> recetas;
    private int id;
    public Paciente(String nombre, String apellido, ObraSocial obraSocial, int id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.obraSocial = obraSocial;
        this.id = id;
        this.clinicaService = ClinicaService.getInstance();
//        PacienteDao pacienteDao;
//        pacienteDao.guardar(this);
        
    }

    public ClinicaService clinicaService = ClinicaService.getInstance();

    public int getId() {
        return id;
    }


    @Override
    public String call() throws Exception {


        // Se me courre  que con randoms se puede crear turnos y despues en otra funcion que se fije lso turnos asignados y ahi vaya a al consulta





            clinicaService.asistirTurno(id);
            try {
                Thread.sleep(10); // Esperar un tiempo antes de asistir al siguiente turno
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        return "El paciente " + nombre + " " + apellido + " ha asistido a su turno.";
    }
}
