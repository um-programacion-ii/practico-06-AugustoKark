import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import programacion2.curso2024.dao.MedicoDao;
import programacion2.curso2024.dao.PacienteDao;
import programacion2.curso2024.entidades.*;
import programacion2.curso2024.services.AtencionMedicoService;
import programacion2.curso2024.services.FarmaciaService;
import programacion2.curso2024.enumeracion.ObraSocial;
import programacion2.curso2024.services.GestionTurnosService;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    @Test
public void testCall() throws Exception {
    try (MockedStatic<AtencionMedicoService> atencionMedicoServiceMockedStatic = Mockito.mockStatic(AtencionMedicoService.class);
         MockedStatic<GestionTurnosService> gestionTurnosServiceMockedStatic = Mockito.mockStatic(GestionTurnosService.class);
         MockedStatic<PacienteDao> pacienteDaoMockedStatic = Mockito.mockStatic(PacienteDao.class);
         MockedStatic<MedicoDao> medicoDaoMockedStatic = Mockito.mockStatic(MedicoDao.class);
         MockedStatic<FarmaciaService> farmaciaServiceMockedStatic = Mockito.mockStatic(FarmaciaService.class)) {

        AtencionMedicoService atencionMedicoServiceMock = mock(AtencionMedicoService.class);
        atencionMedicoServiceMockedStatic.when(AtencionMedicoService::getInstance).thenReturn(atencionMedicoServiceMock);

        GestionTurnosService gestionTurnosServiceMock = mock(GestionTurnosService.class);
        gestionTurnosServiceMockedStatic.when(GestionTurnosService::getInstance).thenReturn(gestionTurnosServiceMock);

        PacienteDao pacienteDaoMock = mock(PacienteDao.class);
        pacienteDaoMockedStatic.when(PacienteDao::getInstance).thenReturn(pacienteDaoMock);

        MedicoDao medicoDaoMock = mock(MedicoDao.class);
        medicoDaoMockedStatic.when(MedicoDao::getInstance).thenReturn(medicoDaoMock);

        FarmaciaService farmaciaServiceMock = mock(FarmaciaService.class);
        farmaciaServiceMockedStatic.when(FarmaciaService::getInstance).thenReturn(farmaciaServiceMock);

        Paciente paciente = new Paciente("Juan", "Perez", ObraSocial.OSDE, 1);

        Medico medicoMock = mock(Medico.class);
        when(medicoMock.getNombre()).thenReturn("Dr. Mock");
        when(medicoMock.getObrasSociales()).thenReturn(Arrays.asList(ObraSocial.OSDE));
        when(medicoMock.isAtiendeParticular()).thenReturn(true);

        when(medicoDaoMock.buscar(anyInt())).thenReturn(medicoMock);

        Map<Integer, Medico> medicosMock = new HashMap<>();
        medicosMock.put(1, medicoMock);
        when(medicoDaoMock.getMedicos()).thenReturn(medicosMock);

        Turno turnoMock = mock(Turno.class);
        when(turnoMock.getMedicoId()).thenReturn(1);
        paciente.getTurnosSolicitados().add(turnoMock);

        paciente.call();

        verify(gestionTurnosServiceMock, times(1)).solicitarTurno(anyInt(), eq(paciente));
        verify(atencionMedicoServiceMock, times(1)).asistirTurno(anyInt(), eq(paciente));
    }
}

    @Test
    public void testCall_MedicoNoAtiendeParticular() throws Exception {
        try (MockedStatic<AtencionMedicoService> atencionMedicoServiceMockedStatic = Mockito.mockStatic(AtencionMedicoService.class);
             MockedStatic<GestionTurnosService> gestionTurnosServiceMockedStatic = Mockito.mockStatic(GestionTurnosService.class);
             MockedStatic<PacienteDao> pacienteDaoMockedStatic = Mockito.mockStatic(PacienteDao.class);
             MockedStatic<MedicoDao> medicoDaoMockedStatic = Mockito.mockStatic(MedicoDao.class);
             MockedStatic<FarmaciaService> farmaciaServiceMockedStatic = Mockito.mockStatic(FarmaciaService.class)) {

            AtencionMedicoService atencionMedicoServiceMock = mock(AtencionMedicoService.class);
            atencionMedicoServiceMockedStatic.when(AtencionMedicoService::getInstance).thenReturn(atencionMedicoServiceMock);

            GestionTurnosService gestionTurnosServiceMock = mock(GestionTurnosService.class);
            gestionTurnosServiceMockedStatic.when(GestionTurnosService::getInstance).thenReturn(gestionTurnosServiceMock);

            PacienteDao pacienteDaoMock = mock(PacienteDao.class);
            pacienteDaoMockedStatic.when(PacienteDao::getInstance).thenReturn(pacienteDaoMock);

            MedicoDao medicoDaoMock = mock(MedicoDao.class);
            medicoDaoMockedStatic.when(MedicoDao::getInstance).thenReturn(medicoDaoMock);

            FarmaciaService farmaciaServiceMock = mock(FarmaciaService.class);
            farmaciaServiceMockedStatic.when(FarmaciaService::getInstance).thenReturn(farmaciaServiceMock);

            Paciente paciente = new Paciente("Juan", "Perez", ObraSocial.OSDE, 1);

            Medico medicoMock = mock(Medico.class);
            when(medicoMock.getNombre()).thenReturn("Dr. Mock");
            when(medicoMock.getObrasSociales()).thenReturn(Arrays.asList(ObraSocial.PAMI)); // No contiene OSDE
            when(medicoMock.isAtiendeParticular()).thenReturn(false); // No atiende particular

            when(medicoDaoMock.buscar(anyInt())).thenReturn(medicoMock);

            Map<Integer, Medico> medicosMock = new HashMap<>();
            medicosMock.put(1, medicoMock);
            when(medicoDaoMock.getMedicos()).thenReturn(medicosMock);

            Turno turnoMock = mock(Turno.class);
            when(turnoMock.getMedicoId()).thenReturn(1);
            paciente.getTurnosSolicitados().add(turnoMock);

            // Capturar el System.out
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            paciente.call();

            // Verificar que se imprime el mensaje correcto
            String expectedOutput = "El paciente Juan Perez ha seleccionado al médico Dr. Mock para su consulta.\nEl médico Dr. Mock no atiende a pacientes particulares.\n";
            assertThat(outContent.toString(), is(expectedOutput));
        }
    }

    @Test
    public void testGetId() {
        // Arrange
        Paciente paciente = new Paciente("Juan", "Perez", ObraSocial.PAMI, 1);

        // Act
        int id = paciente.getId();

        // Assert
        assertEquals(1, id, "The id should be 1");
    }


}