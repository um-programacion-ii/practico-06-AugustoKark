import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.MockedStatic;
import programacion2.curso2024.dao.MedicoDao;
import programacion2.curso2024.dao.TurnoDao;
import programacion2.curso2024.entidades.Medico;
import programacion2.curso2024.entidades.Paciente;
import programacion2.curso2024.enumeracion.ObraSocial;
import programacion2.curso2024.services.GestionTurnosService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.util.Map;

import static org.mockito.Mockito.*;

public class GestionTurnoServiceTest {

    @Mock
    private TurnoDao turnoDaoMock;

    @Mock
    private MedicoDao medicoDaoMock;

    private GestionTurnosService gestionTurnosService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

 @Test
void testSolicitarTurno() {
    try (MockedStatic<TurnoDao> turnoDaoMockedStatic = Mockito.mockStatic(TurnoDao.class);
         MockedStatic<MedicoDao> medicoDaoMockedStatic = Mockito.mockStatic(MedicoDao.class)) {

        turnoDaoMockedStatic.when(TurnoDao::getInstance).thenReturn(turnoDaoMock);
        medicoDaoMockedStatic.when(MedicoDao::getInstance).thenReturn(medicoDaoMock);
        gestionTurnosService = GestionTurnosService.getInstance();

        // Arrange
        int idMedico = 1;
        Paciente paciente = new Paciente("Juan", "Perez", ObraSocial.PAMI, 1);

        // Create a mock Map
        Map<Integer, Medico> medicosMock = Mockito.mock(Map.class);
        when(medicoDaoMock.getMedicos()).thenReturn(medicosMock);
        when(medicosMock.containsKey(idMedico)).thenReturn(true);

        // Create a mock Medico
        Medico medicoMock = Mockito.mock(Medico.class);
        when(medicoDaoMock.buscar(idMedico)).thenReturn(medicoMock);

        // Act
        gestionTurnosService.solicitarTurno(idMedico, paciente);

        // Assert
        verify(turnoDaoMock, times(1)).guardar(any());
    }
}

@Test
void testSolicitarTurnoPrint() {
    try (MockedStatic<MedicoDao> medicoDaoMockedStatic = Mockito.mockStatic(MedicoDao.class);
         MockedStatic<TurnoDao> turnoDaoMockedStatic = Mockito.mockStatic(TurnoDao.class)) {

        medicoDaoMockedStatic.when(MedicoDao::getInstance).thenReturn(medicoDaoMock);
        turnoDaoMockedStatic.when(TurnoDao::getInstance).thenReturn(turnoDaoMock);
        gestionTurnosService = GestionTurnosService.getInstance();

        // Arrange
        int idMedico = 1;
        Paciente paciente = new Paciente("Juan", "Perez", ObraSocial.PAMI, 1);

        // Create a mock Map
        Map<Integer, Medico> medicosMock = Mockito.mock(Map.class);
        when(medicoDaoMock.getMedicos()).thenReturn(medicosMock);
        when(medicosMock.containsKey(idMedico)).thenReturn(true);

        // Create a mock Medico
        Medico medicoMock = Mockito.mock(Medico.class);
        when(medicoDaoMock.buscar(idMedico)).thenReturn(medicoMock);

        // Capturar el System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Act
        gestionTurnosService.solicitarTurno(idMedico, paciente);

        // Assert
        String expectedOutput = "Solicitando turno\nTurno agregado. El paciente ahora tiene 1 turnos.\n";
        assertThat(outContent.toString(), is(expectedOutput));
    }
}
}