package programacion2.curso2024.entidades;

import java.util.HashMap;
import java.util.Map;

public class Farmacia {
    private static Farmacia instance = null;

    // Mapa de medicamentos y sus cantidades
    private Map<Medicamento, Integer> medicametosMap = new HashMap<>();

    public static Farmacia getInstance(){
        if(instance == null){
            instance = new Farmacia();
        }
        return instance;
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
            // Aquí puedes agregar el código para hacer un pedido a la droguería
            // Por ahora, simplemente repondremos el stock del medicamento
            medicametosMap.put(medicamento, 10); // Supongamos que cada pedido a la droguería trae 10 unidades
            System.out.println("Stock repuesto. Entregando medicamento: " + medicamento.getNombre());
        }
    }

    public Integer checkStock(Medicamento medicamento){
        int currentStock = medicametosMap.getOrDefault(medicamento, 0);
        System.out.println("El stock actual del medicamento " + medicamento.getNombre() + " es: " + currentStock);
        return currentStock;
    }
}