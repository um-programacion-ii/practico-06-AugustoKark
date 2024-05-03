package programacion2.curso2024.services;

import programacion2.curso2024.entidades.Medicamento;
import programacion2.curso2024.entidades.Receta;

public class FarmaciaService {

    public static FarmaciaService instance = null;

    public static FarmaciaService getInstance(){
        if(instance == null){
            instance = new FarmaciaService();
        }
        return instance;
    }

    public void entregarMedicamentos(Receta receta) {
        System.out.println("Entregando medicamentos para la receta " + receta.getId());
        for (Medicamento medicamento : receta.getMedicamentos()) {
            System.out.println("Entregando medicamento: " + medicamento.getNombre());
        }
    }
}