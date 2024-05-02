package programacion2.curso2024;

import programacion2.curso2024.dao.MedicoDao;
import programacion2.curso2024.entidades.Medico;
import programacion2.curso2024.entidades.Paciente;
import programacion2.curso2024.enumeracion.ObraSocial;
import programacion2.curso2024.services.ClinicaService;
import programacion2.curso2024.services.GestionTurnosService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        // Crear instancias de los servicios
        GestionTurnosService gestionTurnosService = new GestionTurnosService();
        ClinicaService clinicaService = ClinicaService.getInstance();

        // Crear un médico
        Medico medico = new Medico();
        medico.setId(1);
        medico.setEstado(Medico.Estado.LIBRE);

        // Guardar el médico en MedicoDao
        MedicoDao medicoDao = new MedicoDao();
        medicoDao.guardar(medico);

        // Crear un ExecutorService para manejar los hilos
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Crear un Paciente (hilo) y enviarlo al ExecutorService
        Paciente paciente = new Paciente("Juan", "Perez", ObraSocial.PAMI, 1);



        Future<String> resultado = executor.submit(paciente);

        // Aquí puedes hacer algo con el resultado del hilo, si lo necesitas
        // Por ejemplo, podrías imprimir el resultado cuando el hilo termine
        try {
            System.out.println(resultado.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // No olvides cerrar el ExecutorService cuando hayas terminado
        executor.shutdown();
    }
}