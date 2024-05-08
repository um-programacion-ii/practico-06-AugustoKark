package programacion2.curso2024.dao;
import programacion2.curso2024.entidades.Receta;

import java.util.HashMap;
import java.util.Map;

public class RecetaDao {
    private Map<Integer, Receta> recetas = new HashMap<>();

    public static RecetaDao instance = null;

    public static RecetaDao getInstance(){
        if(instance == null){
            instance = new RecetaDao();
        }
        return instance;
    }

    public void guardar(Receta receta){
        if (!recetas.containsKey(receta.getId())) {
            recetas.put(receta.getId(), receta);
        }
    }

//    public void borrar(int id){
//        recetas.remove(id);
//    }
//
//    public Receta buscar(int id){
//        return recetas.get(id);
//    }
//
//    public void modificar(int id, Receta receta){
//        recetas.put(id, receta);
//    }
//
//    public Map<Integer, Receta> getRecetas(){
//        return recetas;
//    }





}
