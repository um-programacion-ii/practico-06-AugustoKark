package programacion2.curso2024;

import programacion2.curso2024.dao.MedicoDao;
import programacion2.curso2024.entidades.Medico;
import programacion2.curso2024.entidades.Paciente;
import programacion2.curso2024.enumeracion.ObraSocial;
import programacion2.curso2024.services.ClinicaService;
import programacion2.curso2024.services.GestionTurnosService;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        // Crear instancias de los servicios
        GestionTurnosService gestionTurnosService = GestionTurnosService.getInstance();
        ClinicaService clinicaService = ClinicaService.getInstance();
        MedicoDao medicoDao = MedicoDao.getInstance();

        // Crear un médico
        Medico medico = new Medico("Juan", Medico.Especialidad.CARDIOLOGIA, true,  1);
        medico.setObrasSociales(Arrays.asList(ObraSocial.PAMI, ObraSocial.OSDE));

        Medico medico2 = new Medico("Pedro", Medico.Especialidad.CARDIOLOGIA, false,  2);
        medico2.setObrasSociales(Arrays.asList(ObraSocial.MEDIFE, ObraSocial.SWISS_MEDICAL, ObraSocial.PAMI));
        medicoDao.guardar(medico);
        medicoDao.guardar(medico2);

        // Crear un ExecutorService para manejar los hilos
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Crear un Paciente (hilo) y enviarlo al ExecutorService
        Paciente paciente = new Paciente("Juan", "Perez", ObraSocial.PAMI, 123);



        Future<String> resultado = executor.submit(paciente);


        try {
            System.out.println(resultado.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}