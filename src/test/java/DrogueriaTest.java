
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import programacion2.curso2024.dao.FarmaciaDao;
import programacion2.curso2024.entidades.Drogueria;
import programacion2.curso2024.entidades.Medicamento;
import static org.mockito.Mockito.*;

public class DrogueriaTest {

    @Test
    public void testHacerPedido(){
        try (MockedStatic<FarmaciaDao> farmaciaDaoMockedStatic = Mockito.mockStatic(FarmaciaDao.class)) {
            FarmaciaDao farmaciaDaoMock = mock(FarmaciaDao.class);
            farmaciaDaoMockedStatic.when(FarmaciaDao::getInstance).thenReturn(farmaciaDaoMock);

            Medicamento medicamento = new Medicamento("Ibuprofeno", "1 comprimido", "Cada 8 horas");
            Drogueria.getInstance().hacerPedido(medicamento);

            verify(farmaciaDaoMock, times(1)).aumentarStock(10, medicamento);
        }
    }
}
