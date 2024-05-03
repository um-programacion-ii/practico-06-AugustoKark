package programacion2.curso2024.dao;

import programacion2.curso2024.entidades.Medico;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarOutputStream;

public class MedicoDao {
    private Map<Integer, Medico> medicos = new HashMap<>();

    public static MedicoDao instance = null;

    public static MedicoDao getInstance(){
        if(instance == null){
            instance = new MedicoDao();
        }
        return instance;
    }



    public void guardar(Medico medico){
    if (!medicos.containsKey(medico.getId())) {
        medicos.put(medico.getId(), medico);

    }
}
    public void borrar(int id){
        medicos.remove(id);
    }
    public Medico buscar(int id){
        return medicos.get(id);
    }
    public void modificar(int id, Medico medico){
        medicos.put(id, medico);
    }
    public Map<Integer, Medico> getMedicos(){


        return medicos;
    }



}
