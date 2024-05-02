package programacion2.curso2024.services;

import programacion2.curso2024.dao.MedicoDao;
import programacion2.curso2024.entidades.Paciente;
import programacion2.curso2024.entidades.Medico;

public class ClinicaService {

    public static ClinicaService instance = null;

    public static  ClinicaService getInstance(){
        if(instance == null){
            instance = new ClinicaService();
        }
        return instance;
    }
    private MedicoDao medicoDao = new MedicoDao();

    public void asistirTurno(int idMedico){
        if(medicoDao.getMedicos().containsKey(idMedico)){
            Medico medico = medicoDao.buscar(idMedico);
            medico.setEstado(Medico.Estado.LIBRE);
            medicoDao.modificar(idMedico, medico);
        }
    }
}