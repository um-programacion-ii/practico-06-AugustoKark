import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import programacion2.curso2024.dao.RecetaDao;
import programacion2.curso2024.entidades.Medico;
import programacion2.curso2024.entidades.Medicamento;
import programacion2.curso2024.entidades.Receta;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class MedicoTest {

    @Test
    public void testCrearReceta() {
        try (MockedStatic<RecetaDao> recetaDaoMockedStatic = Mockito.mockStatic(RecetaDao.class)) {
            RecetaDao recetaDaoMock = mock(RecetaDao.class);
            recetaDaoMockedStatic.when(RecetaDao::getInstance).thenReturn(recetaDaoMock);

            Medico medico = new Medico("Dr. House", Medico.Especialidad.CLINICA, true, 1);
            List<Medicamento> medicamentos = Arrays.asList(new Medicamento("Ibuprofeno", "1 comprimido", "Cada 8 horas"));
            Receta receta = medico.crearReceta(medicamentos);

            verify(recetaDaoMock, times(1)).guardar(receta);
        }
    }
}
