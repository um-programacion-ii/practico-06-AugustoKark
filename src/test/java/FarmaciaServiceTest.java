import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import programacion2.curso2024.dao.FarmaciaDao;
import programacion2.curso2024.entidades.Medicamento;
import programacion2.curso2024.entidades.Receta;
import programacion2.curso2024.services.FarmaciaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class FarmaciaServiceTest {

    @Mock
    private FarmaciaDao farmaciaDaoMock;

    private FarmaciaService farmaciaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void testEntregarMedicamentos_StockSuficiente() {
        try (MockedStatic<FarmaciaDao> farmaciaDaoMockedStatic = Mockito.mockStatic(FarmaciaDao.class)) {
            farmaciaDaoMockedStatic.when(FarmaciaDao::getInstance).thenReturn(farmaciaDaoMock);
            farmaciaService = FarmaciaService.getInstance();

            // Arrange
            Receta receta = new Receta();
            List<Medicamento> medicamentos = new ArrayList<>();
            Medicamento medicamento = new Medicamento("Ibuprofeno", "1 comprimido", "Cada 8 horas");
            medicamentos.add(medicamento);
            receta.setMedicamentos(medicamentos);
            when(farmaciaDaoMock.checkStock(any(Medicamento.class))).thenReturn(10); // Simulamos suficiente stock

            // Act
            farmaciaService.entregarMedicamentos(receta);

            // Assert
            verify(farmaciaDaoMock, times(1)).reducirStock(eq(1), eq(medicamento)); // Se debe reducir el stock en 1 unidad
        }
    }

    @Test
    void testEntregarMedicamentos_StockInsuficiente() {
        try (MockedStatic<FarmaciaDao> farmaciaDaoMockedStatic = Mockito.mockStatic(FarmaciaDao.class)) {
            farmaciaDaoMockedStatic.when(FarmaciaDao::getInstance).thenReturn(farmaciaDaoMock);
            farmaciaService = FarmaciaService.getInstance();

            // Arrange
            Receta receta = new Receta();
            List<Medicamento> medicamentos = new ArrayList<>();
            Medicamento medicamento = new Medicamento("Ibuprofeno", "1 comprimido", "Cada 8 horas");
            medicamentos.add(medicamento);
            receta.setMedicamentos(medicamentos);
            when(farmaciaDaoMock.checkStock(any(Medicamento.class))).thenReturn(0); // Simulamos stock insuficiente

            // Act
            farmaciaService.entregarMedicamentos(receta);

            // Assert
            InOrder inOrder = inOrder(farmaciaDaoMock);
            inOrder.verify(farmaciaDaoMock, times(1)).aumentarStock(eq(10), eq(medicamento)); // Se debe aumentar el stock en 10 unidades
            inOrder.verify(farmaciaDaoMock, times(1)).reducirStock(eq(1), eq(medicamento)); // Luego se debe reducir el stock en 1 unidad
        }
    }
}
