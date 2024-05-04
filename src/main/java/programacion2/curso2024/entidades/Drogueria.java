package programacion2.curso2024.entidades;

import programacion2.curso2024.dao.FarmaciaDao;

public class Drogueria {

private static Drogueria instance = null;



    public static Drogueria getInstance(){
        if(instance == null){
            instance = new Drogueria();
        }
        return instance;
    }

    public void hacerPedido(Medicamento medicamento){
        System.out.println("Haciendo pedido a la drogueria");
FarmaciaDao.getInstance().aumentarStock(10, medicamento);


    }

}
