package com.esfera.camunda.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalcularMediaPontosBeneficiosTest {

    @Mock
    private DelegateExecution delegateExecution;

    private CalcularMediaPontosBeneficios calcularMediaPontosBeneficios;

    @BeforeEach
    void setUp() {
        calcularMediaPontosBeneficios = new CalcularMediaPontosBeneficios();
    }

    @Test
    void testExecute_CalculoValido() throws Exception {
        // Arrange
        Integer qtdePontos = 100;
        Long beneficio = 10L;
        when(delegateExecution.getVariable("qtdePontos")).thenReturn(qtdePontos);
        when(delegateExecution.getVariable("beneficio")).thenReturn(beneficio);

        // Act
        assertDoesNotThrow(() -> calcularMediaPontosBeneficios.execute(delegateExecution));

        // Assert
        verify(delegateExecution).getVariable("qtdePontos");
        verify(delegateExecution).getVariable("beneficio");
    }

    @Test
    void testExecute_ComPontosZero() throws Exception {
        // Arrange
        Integer qtdePontos = 0;
        Long beneficio = 10L;
        when(delegateExecution.getVariable("qtdePontos")).thenReturn(qtdePontos);
        when(delegateExecution.getVariable("beneficio")).thenReturn(beneficio);

        // Act & Assert
        assertDoesNotThrow(() -> calcularMediaPontosBeneficios.execute(delegateExecution));
        verify(delegateExecution).getVariable("qtdePontos");
        verify(delegateExecution).getVariable("beneficio");
    }

    @Test
    void testExecute_ComBeneficioUm() throws Exception {
        // Arrange
        Integer qtdePontos = 50;
        Long beneficio = 1L;
        when(delegateExecution.getVariable("qtdePontos")).thenReturn(qtdePontos);
        when(delegateExecution.getVariable("beneficio")).thenReturn(beneficio);

        // Act & Assert
        assertDoesNotThrow(() -> calcularMediaPontosBeneficios.execute(delegateExecution));
        verify(delegateExecution).getVariable("qtdePontos");
        verify(delegateExecution).getVariable("beneficio");
    }

    @Test
    void testExecute_ComVariaveisNull() {
        // Arrange
        when(delegateExecution.getVariable("qtdePontos")).thenReturn(null);
        when(delegateExecution.getVariable("beneficio")).thenReturn(null);

        // Act & Assert
        assertThrows(Exception.class, () -> calcularMediaPontosBeneficios.execute(delegateExecution));
    }

    @Test
    void testExecute_ComBeneficioZero() {
        // Arrange
        Integer qtdePontos = 100;
        Long beneficio = 0L;
        when(delegateExecution.getVariable("qtdePontos")).thenReturn(qtdePontos);
        when(delegateExecution.getVariable("beneficio")).thenReturn(beneficio);

        // Act & Assert - Deve lançar ArithmeticException para divisão por zero
        assertThrows(ArithmeticException.class, () -> calcularMediaPontosBeneficios.execute(delegateExecution));
    }
}