import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.MockedStatic;
import programacion2.curso2024.dao.MedicoDao;
import programacion2.curso2024.entidades.Medico;
import programacion2.curso2024.entidades.Paciente;
import programacion2.curso2024.entidades.Turno;
import programacion2.curso2024.enumeracion.ObraSocial;
import programacion2.curso2024.services.AtencionMedicoService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.util.Map;
import java.util.Random;

import static org.mockito.Mockito.*;

public class AtencionMedicoServiceTest {

    @Mock
    private MedicoDao medicoDaoMock;

    private AtencionMedicoService atencionMedicoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.reset(medicoDaoMock);
    }

@Test
void testAsistirTurno() {
    Mockito.reset(medicoDaoMock);
    try (MockedStatic<MedicoDao> medicoDaoMockedStatic = Mockito.mockStatic(MedicoDao.class)) {



        medicoDaoMockedStatic.when(MedicoDao::getInstance).thenReturn(medicoDaoMock);

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

        // Create a mock Turno and add it to the Paciente's turnosSolicitados
        Turno turnoMock = Mockito.mock(Turno.class);
        paciente.getTurnosSolicitados().add(turnoMock);

        // Initialize AtencionMedicoService after setting up the MedicoDao mock
        atencionMedicoService = AtencionMedicoService.getInstance();

        // Act
        atencionMedicoService.asistirTurno(idMedico, paciente);

        // Assert
        verify(medicoDaoMock, times(2)).modificar(eq(idMedico), any(Medico.class));
    }
}


@Test
void testAsistirTurnoPrint() {
    Mockito.reset(medicoDaoMock);
    try (MockedStatic<MedicoDao> medicoDaoMockedStatic = Mockito.mockStatic(MedicoDao.class)) {

        medicoDaoMockedStatic.when(MedicoDao::getInstance).thenReturn(medicoDaoMock);
        atencionMedicoService = AtencionMedicoService.getInstance();

        // Mock the Random object
        Random randomMock = Mockito.mock(Random.class);
        atencionMedicoService.random = randomMock;
        when(randomMock.nextInt(2)).thenReturn(1); // Always return 1 to simulate that the doctor always prescribes medications

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

        // Create a mock Turno and add it to the Paciente's turnosSolicitados
        Turno turnoMock = Mockito.mock(Turno.class);
        paciente.getTurnosSolicitados().add(turnoMock);

        // Capturar el System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Act
        atencionMedicoService.asistirTurno(idMedico, paciente);

        // Assert
        String expectedOutput = "El paciente Juan Perez ha sido atendido por el médico \nEl medico receto medicamentos\n";
        assertThat(outContent.toString(), is(expectedOutput));
    }
}
}