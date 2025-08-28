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
class NotificarClientePontosInvalidosTest {

    @Mock
    private DelegateExecution delegateExecution;

    private NotificarClientePontosInvalidos notificarClientePontosInvalidos;

    @BeforeEach
    void setUp() {
        notificarClientePontosInvalidos = new NotificarClientePontosInvalidos();
    }

    @Test
    void testExecute_ComSolicitanteValido() throws Exception {
        // Arrange
        String solicitante = "João Silva";
        when(delegateExecution.getVariable("solicitante")).thenReturn(solicitante);

        // Act
        assertDoesNotThrow(() -> notificarClientePontosInvalidos.execute(delegateExecution));

        // Assert
        verify(delegateExecution).getVariable("solicitante");
    }

    @Test
    void testExecute_ComSolicitanteNull() throws Exception {
        // Arrange
        when(delegateExecution.getVariable("solicitante")).thenReturn(null);

        // Act & Assert
        assertDoesNotThrow(() -> notificarClientePontosInvalidos.execute(delegateExecution));
        verify(delegateExecution).getVariable("solicitante");
    }

    @Test
    void testExecute_ComSolicitanteVazio() throws Exception {
        // Arrange
        String solicitante = "";
        when(delegateExecution.getVariable("solicitante")).thenReturn(solicitante);

        // Act & Assert
        assertDoesNotThrow(() -> notificarClientePontosInvalidos.execute(delegateExecution));
        verify(delegateExecution).getVariable("solicitante");
    }

    @Test
    void testExecute_ComNomeCompletoSolicitante() throws Exception {
        // Arrange
        String solicitante = "Maria dos Santos Silva";
        when(delegateExecution.getVariable("solicitante")).thenReturn(solicitante);

        // Act & Assert
        assertDoesNotThrow(() -> notificarClientePontosInvalidos.execute(delegateExecution));
        verify(delegateExecution).getVariable("solicitante");
    }

    @Test
    void testExecute_ComCaracteresEspeciais() throws Exception {
        // Arrange
        String solicitante = "João da Silva & Cia";
        when(delegateExecution.getVariable("solicitante")).thenReturn(solicitante);

        // Act & Assert
        assertDoesNotThrow(() -> notificarClientePontosInvalidos.execute(delegateExecution));
        verify(delegateExecution).getVariable("solicitante");
    }

    @Test
    void testExecute_VerificaChamadaUnicaGetVariable() throws Exception {
        // Arrange
        String solicitante = "Pedro Costa";
        when(delegateExecution.getVariable("solicitante")).thenReturn(solicitante);

        // Act
        notificarClientePontosInvalidos.execute(delegateExecution);

        // Assert - Verifica se getVariable foi chamado exatamente uma vez
        verify(delegateExecution, times(1)).getVariable("solicitante");
        verifyNoMoreInteractions(delegateExecution);
    }
}