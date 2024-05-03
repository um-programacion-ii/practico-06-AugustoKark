package programacion2.curso2024.dao;

import programacion2.curso2024.Main;
import programacion2.curso2024.entidades.Turno;

import java.util.HashMap;
import java.util.Map;

public class TurnoDao {
    private Map<Integer, Turno> turnos = new HashMap<>();

    public static TurnoDao instance = null;

    public static TurnoDao getInstance(){
        if(instance == null){
            instance = new TurnoDao();
        }
        return instance;
    }

    public void guardar(Turno turno){
        if (!turnos.containsKey(turno.getId())) {
            turnos.put(turno.getId(), turno);
        }
    }

    public void borrar(int id){
        turnos.remove(id);
    }

    public Turno buscar(int id){
        return turnos.get(id);
    }

    public void modificar(int id, Turno turno){
        turnos.put(id, turno);
    }

    public Map<Integer, Turno> getTurnos(){
        return turnos;
    }

}
