package programacion2.curso2024.entidades;

import lombok.Data;

@Data
public class Turno {
    private  int medicoId ;
    private Paciente paciente;
    private int id;


    public int getId() {
        return id;
    }

    public void setId(int i) {
    }
}
