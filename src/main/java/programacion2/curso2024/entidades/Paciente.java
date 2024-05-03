package programacion2.curso2024.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import programacion2.curso2024.dao.MedicoDao;
import programacion2.curso2024.dao.PacienteDao;
import programacion2.curso2024.enumeracion.ObraSocial;
import programacion2.curso2024.services.ClinicaService;
import programacion2.curso2024.services.GestionTurnosService;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente implements Callable {

    private String nombre;
    private String apellido;
    private ObraSocial obraSocial;
    private List<Receta> recetas;
    private List<Turno> turnosSolicitados = new ArrayList<>();
    private int id;




    public Paciente(String nombre, String apellido, ObraSocial obraSocial, int id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.obraSocial = obraSocial;
        this.id = id;
        this.clinicaService = ClinicaService.getInstance();

        
    }

    public ClinicaService clinicaService = ClinicaService.getInstance();
    public GestionTurnosService gestionTurnosService = GestionTurnosService.getInstance();
    public PacienteDao pacienteDao = PacienteDao.getInstance();
    public MedicoDao medicoDao = MedicoDao.getInstance();
    public int getId() {
        return id;
    }


    @Override
    public synchronized String call() throws Exception {
        boolean continuar = true;


        //selecionar un medico al azar de la lista de medicos en medicoDao
        Random random = new Random();
        int randomMedico = random.nextInt(medicoDao.getMedicos().size())+1;
        Medico medico = medicoDao.buscar(randomMedico);
        System.out.println("El paciente " + nombre + " " + apellido + " ha seleccionado al médico " + medico.getNombre() + " para su consulta.");



        if (!medico.getObrasSociales().contains(this.obraSocial) && !medico.isAtiendeParticular()) {
            System.out.println("El médico " + medico.getNombre() + " no atiende a pacientes particulares.");
            continuar = false;
        }

        if (continuar){




        // Solicitar turno
        gestionTurnosService.solicitarTurno(medico.getId(), this);
        System.out.println("El paciente " + nombre + " " + apellido + " ha solicitado un turno.");


        // Asistir al turno
        for (Turno turno : turnosSolicitados) {
            clinicaService.asistirTurno(1, this);
            Thread.sleep(10);
            System.out.println("El paciente " + nombre + " " + apellido + " ha asistido a su turno.");
        }

            try {
                Thread.sleep(10); // Esperar un tiempo antes de asistir al siguiente turno
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        return ".";
    }
        return "";
    }
}
