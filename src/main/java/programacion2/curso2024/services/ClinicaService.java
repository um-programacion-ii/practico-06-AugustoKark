package programacion2.curso2024.services;

import programacion2.curso2024.dao.MedicoDao;
import programacion2.curso2024.entidades.Paciente;
import programacion2.curso2024.entidades.Medico;
import programacion2.curso2024.entidades.Turno;

public class ClinicaService {

    public static ClinicaService instance = null;

    public static  ClinicaService getInstance(){
        if(instance == null){
            instance = new ClinicaService();
        }
        return instance;
    }
    private MedicoDao medicoDao = new MedicoDao();

    public synchronized void asistirTurno(int idMedico,Paciente paciente){
        if(medicoDao.getMedicos().containsKey(idMedico)){
            Medico medico = medicoDao.buscar(idMedico);
            medico.setEstado(Medico.Estado.LIBRE);
            medicoDao.modificar(idMedico, medico);
            Turno turno = paciente.getTurnosSolicitados().get(paciente.getTurnosSolicitados().size()-1);

            if (paciente.getTurnosSolicitados().contains(turno)) {
                medico.setEstado(Medico.Estado.LIBRE);
                medicoDao.modificar(idMedico, medico);
            } else {
                System.out.println("El paciente no tiene turnos solicitados");
        }}
    }
}