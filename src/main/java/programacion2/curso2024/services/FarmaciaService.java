package programacion2.curso2024.services;

import programacion2.curso2024.dao.FarmaciaDao;
import programacion2.curso2024.entidades.Farmacia;
import programacion2.curso2024.entidades.Medicamento;
import programacion2.curso2024.entidades.Receta;

import java.util.HashMap;
import java.util.Map;

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

        FarmaciaDao farmaciaDao = FarmaciaDao.getInstance();

        for (Medicamento medicamento : receta.getMedicamentos()) {

            if (farmaciaDao.checkStock(medicamento) > 0) {
                System.out.println("Entregando medicamento: " + medicamento.getNombre());
                farmaciaDao.reducirStock(1, medicamento);
            } else {
                System.out.println("No hay stock del medicamento " + medicamento.getNombre());
                System.out.println("Haciendo pedido a la droguería...");
                farmaciaDao.aumentarStock(10, medicamento);
                System.out.println("Stock repuesto. Entregando medicamento: " + medicamento.getNombre());
                farmaciaDao.reducirStock(1, medicamento);
            }
        }




    }
}
