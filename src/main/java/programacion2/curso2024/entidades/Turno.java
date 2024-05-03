package programacion2.curso2024.entidades;

import lombok.Data;

import java.util.Optional;

@Data
public class Turno {
    private  int medicoId ;
    private Paciente paciente;
    private int id;
    private Optional<Receta> receta = Optional.empty();


    public int getId() {
        return id;
    }

    public void setId(int i) {
    }
}
