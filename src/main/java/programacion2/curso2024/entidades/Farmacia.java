package programacion2.curso2024.entidades;

import programacion2.curso2024.enumeracion.ObraSocial;

import java.util.List;
import java.util.Map;

public class Farmacia {
    private String nombre;
    private String direccion;
    private Map<Medicamento, Integer> stock;
    private List<ObraSocial> obrasSociales;

}
