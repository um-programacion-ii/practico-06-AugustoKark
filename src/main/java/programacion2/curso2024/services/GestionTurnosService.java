package programacion2.curso2024.services;

import programacion2.curso2024.dao.MedicoDao;
import programacion2.curso2024.dao.TurnoDao;
import programacion2.curso2024.entidades.Paciente;
import programacion2.curso2024.entidades.Turno;
import programacion2.curso2024.entidades.Medico;

import java.util.Map;
import java.util.HashMap;

public class GestionTurnosService {
    private Map<Integer, Turno> turnos = new HashMap<>();
    private MedicoDao medicoDao = MedicoDao.getInstance();
    private int turnoIdCounter = 1;
    TurnoDao turnoDao = TurnoDao.getInstance();



    private static GestionTurnosService instance = null;

    public static GestionTurnosService getInstance(){
        if(instance == null){
            instance = new GestionTurnosService();
        }
        return instance;
    }




    public void solicitarTurno(int idMedico, Paciente paciente){
        System.out.println("Solicitando turno");
        Turno turno = new Turno();
        turno.setId(turnoIdCounter++);




        if(medicoDao.getMedicos().containsKey(idMedico)){
            Medico medico = medicoDao.buscar(idMedico);
            medico.setEstado(Medico.Estado.OCUPADO);
            medicoDao.modificar(idMedico, medico);


            turnos.put(turno.getId(), turno);

            paciente.getTurnosSolicitados().add(turno);
            System.out.println("Turno agregado. El paciente ahora tiene " + paciente.getTurnosSolicitados().size() + " turnos.");

            turnoDao.guardar(turno);
        }else{
            System.out.println("El medico no existe");
        }
    }
}