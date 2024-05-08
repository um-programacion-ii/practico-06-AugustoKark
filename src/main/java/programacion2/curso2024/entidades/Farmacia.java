package programacion2.curso2024.entidades;

import java.util.HashMap;
import java.util.Map;

public class Farmacia {
    private static Farmacia instance = null;

    // Mapa de medicamentos y sus cantidades
//    private Map<Medicamento, Integer> medicametosMap = new HashMap<>();

    public static Farmacia getInstance(){
        if(instance == null){
            instance = new Farmacia();
        }
        return instance;
    }


}