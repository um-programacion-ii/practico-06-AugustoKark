// FarmaciaDao.java
package programacion2.curso2024.dao;

import programacion2.curso2024.entidades.Drogueria;
import programacion2.curso2024.entidades.Medicamento;

import java.util.HashMap;
import java.util.Map;

public class FarmaciaDao {
    private Map<Medicamento, Integer> medicametosMap = new HashMap<>();
    private Drogueria drogueria = Drogueria.getInstance();

    public static FarmaciaDao instance = null;

    public static FarmaciaDao getInstance(){
        if(instance == null){
            instance = new FarmaciaDao();
        }
        return instance;
    }

    private FarmaciaDao(){

        medicametosMap.put(new Medicamento("Ibuprofeno", "1 comprimido", "Cada 8 horas"), 10);
        medicametosMap.put(new Medicamento("Paracetamol", "1 comprimido", "Cada 6 horas"), 10);
        medicametosMap.put(new Medicamento("Amoxicilina", "1 comprimido", "Cada 12 horas"), 10);
    }


    public void aumentarStock(int cantidad, Medicamento medicamento){
        int currentStock = medicametosMap.getOrDefault(medicamento, 0);
        medicametosMap.put(medicamento, currentStock + cantidad);
    }

    public void reducirStock(int cantidad, Medicamento medicamento){
        int currentStock = medicametosMap.getOrDefault(medicamento, 0);
        if (currentStock >= cantidad) {
            medicametosMap.put(medicamento, currentStock - cantidad);
        } else {
            System.out.println("No hay suficiente stock del medicamento: " + medicamento.getNombre());
            drogueria.hacerPedido(medicamento);
//
            System.out.println("Stock repuesto. Entregando medicamento: " + medicamento.getNombre());
        }
    }

    public Integer checkStock(Medicamento medicamento){
        int currentStock = medicametosMap.getOrDefault(medicamento, 0);
        System.out.println("El stock actual del medicamento " + medicamento.getNombre() + " es: " + currentStock);
        return currentStock;
    }
}