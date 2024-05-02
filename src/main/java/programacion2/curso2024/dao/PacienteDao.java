package programacion2.curso2024.dao;

import programacion2.curso2024.entidades.Paciente;

import java.util.HashMap;
import java.util.Map;

public class PacienteDao {
    private Map<Integer, Paciente> pacientes= new HashMap<>();


    public void guardar(Paciente paciente){
    if (!pacientes.containsKey(paciente.getId())) {
        pacientes.put(paciente.getId(), paciente);
    }
}
    public void borrar(int id){
        pacientes.remove(id);
    }

    public Paciente buscar(int id){
        return pacientes.get(id);
    }

    public void modificar(int id, Paciente paciente){
        pacientes.put(id, paciente);
    }

    public Map<Integer, Paciente> getPacientes(){
        return pacientes;
    }



}
