import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import programacion2.curso2024.entidades.Medicamento;
import programacion2.curso2024.entidades.Paciente;
import programacion2.curso2024.entidades.Receta;
import programacion2.curso2024.entidades.Turno;
import programacion2.curso2024.services.FarmaciaService;
import programacion2.curso2024.enumeracion.ObraSocial;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class PacienteTest {

    @Test
    public void testPedirMedicamentos() {
        try (MockedStatic<FarmaciaService> farmaciaServiceMockedStatic = Mockito.mockStatic(FarmaciaService.class)) {
            FarmaciaService farmaciaServiceMock = mock(FarmaciaService.class);
            farmaciaServiceMockedStatic.when(FarmaciaService::getInstance).thenReturn(farmaciaServiceMock);

            Paciente paciente = new Paciente("Juan", "Perez", ObraSocial.OSDE, 1);
            List<Medicamento> medicamentos = Arrays.asList(new Medicamento("Ibuprofeno", "1 comprimido", "Cada 8 horas"));
            Receta receta = new Receta(1, 1, medicamentos);
            Turno turno = new Turno();
            turno.setReceta(Optional.of(receta));
            paciente.pedirMedicamentos(turno);

            verify(farmaciaServiceMock, times(1)).entregarMedicamentos(receta);
        }
    }
}