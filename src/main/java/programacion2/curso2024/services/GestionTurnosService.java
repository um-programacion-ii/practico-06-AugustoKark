package programacion2.curso2024.services;

import programacion2.curso2024.dao.MedicoDao;
import programacion2.curso2024.entidades.Turno;
import programacion2.curso2024.entidades.Medico;

import java.util.Map;
import java.util.HashMap;

public class GestionTurnosService {
    private Map<Integer, Turno> turnos = new HashMap<>();
    private MedicoDao medicoDao = new MedicoDao();
    private int turnoIdCounter = 1;

    public void addTurno(Turno turno){
        turnos.put(turno.getId(), turno);
    }

    public void asignarTurnoAMedico(int idMedico, int idTurno) {
        if (medicoDao.getMedicos().containsKey(idMedico) && turnos.containsKey(idTurno)) {
            Medico medico = medicoDao.buscar(idMedico);
            medico.setEstado(Medico.Estado.OCUPADO);
            medicoDao.modificar(idMedico, medico);
        }
    }
    public void solicitarTurno(int idMedico){
        // Crear un nuevo turno
        Turno turno = new Turno();
        turno.setId(turnoIdCounter++);

        // Asignar el turno al médico
        if(medicoDao.getMedicos().containsKey(idMedico)){
            Medico medico = medicoDao.buscar(idMedico);
            medico.setEstado(Medico.Estado.OCUPADO);
            medicoDao.modificar(idMedico, medico);

            // Guardar el turno en el mapa de turnos
            turnos.put(turno.getId(), turno);
        }
    }
}