package programacion2.curso2024.services;

import programacion2.curso2024.dao.MedicoDao;
import programacion2.curso2024.entidades.Medicamento;
import programacion2.curso2024.entidades.Paciente;
import programacion2.curso2024.entidades.Medico;
import programacion2.curso2024.entidades.Turno;
import programacion2.curso2024.entidades.Receta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class AtencionMedicoService {

    public static AtencionMedicoService instance = null;

    public Random random = new Random();

    public static AtencionMedicoService getInstance(){
        if(instance == null){
            instance = new AtencionMedicoService();
        }
        return instance;
    }
    private MedicoDao medicoDao = MedicoDao.getInstance();

    public synchronized void asistirTurno(int idMedico,Paciente paciente){


        if(medicoDao.getMedicos().containsKey(idMedico)){

            Medico medico = medicoDao.buscar(idMedico);
            medico.setEstado(Medico.Estado.LIBRE);
            medicoDao.modificar(idMedico, medico);
            Turno turno = paciente.getTurnosSolicitados().get(paciente.getTurnosSolicitados().size()-1);



            System.out.println("El paciente " + paciente.getNombre() + " " + paciente.getApellido() + " ha sido atendido por el médico " );
            if (paciente.getTurnosSolicitados().contains(turno)) {
                medico.setEstado(Medico.Estado.LIBRE);
                medicoDao.modificar(idMedico, medico);
            } else {
                System.out.println("El paciente no tiene turnos solicitados");
        }
            int recetaInt = random.nextInt(2);



            if(recetaInt == 0){

                System.out.println("El medico no receto medicamentos");
            }
            if(recetaInt == 1){

                System.out.println("El medico receto medicamentos");
                List<Medicamento> medicamentos = Medicamento.generarMedicamentos();
                List<Medicamento> medicamentosReceta = new ArrayList<>();
                for (int i = 0; i < 2; i++) {
                    int index = random.nextInt(medicamentos.size());
                    medicamentosReceta.add(medicamentos.get(index));
                    medicamentos.remove(index); // remove the selected medicament to avoid repetition
                }

                // Crear la receta con los medicamentos seleccionados
                Receta nuevaReceta = medico.crearReceta(medicamentosReceta);

                // Asignar la receta al turno
                turno.setReceta(Optional.ofNullable(nuevaReceta));


            }

        }

    }
}