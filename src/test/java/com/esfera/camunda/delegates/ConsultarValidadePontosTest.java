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
class ConsultarValidadePontosTest {

    @Mock
    private DelegateExecution delegateExecution;

    private ConsultarValidadePontos consultarValidadePontos;

    @BeforeEach
    void setUp() {
        consultarValidadePontos = new ConsultarValidadePontos();
    }

    @Test
    void testExecute_ComParametrosValidos() throws Exception {
        // Arrange
        String codSolicitante = "SOL001";
        String solicitante = "João Silva";
        Integer qtdePontos = 500;
        
        when(delegateExecution.getVariable("codSolicitante")).thenReturn(codSolicitante);
        when(delegateExecution.getVariable("solicitante")).thenReturn(solicitante);
        when(delegateExecution.getVariable("qtdePontos")).thenReturn(qtdePontos);

        // Act
        assertDoesNotThrow(() -> consultarValidadePontos.execute(delegateExecution));

        // Assert
        verify(delegateExecution).getVariable("codSolicitante");
        verify(delegateExecution).getVariable("solicitante");
        verify(delegateExecution).getVariable("qtdePontos");
        verify(delegateExecution).setVariable("validadePonto", true);
    }

    @Test
    void testExecute_VerificaSetVariableValidadePonto() throws Exception {
        // Arrange
        String codSolicitante = "SOL002";
        String solicitante = "Maria Santos";
        Integer qtdePontos = 1000;
        
        when(delegateExecution.getVariable("codSolicitante")).thenReturn(codSolicitante);
        when(delegateExecution.getVariable("solicitante")).thenReturn(solicitante);
        when(delegateExecution.getVariable("qtdePontos")).thenReturn(qtdePontos);

        // Act
        consultarValidadePontos.execute(delegateExecution);

        // Assert - Verifica se a variável validadePonto foi definida como true (valor hardcoded no código)
        verify(delegateExecution).setVariable("validadePonto", true);
    }

    @Test
    void testExecute_ComPontosZero() throws Exception {
        // Arrange
        String codSolicitante = "SOL003";
        String solicitante = "Pedro Costa";
        Integer qtdePontos = 0;
        
        when(delegateExecution.getVariable("codSolicitante")).thenReturn(codSolicitante);
        when(delegateExecution.getVariable("solicitante")).thenReturn(solicitante);
        when(delegateExecution.getVariable("qtdePontos")).thenReturn(qtdePontos);

        // Act & Assert
        assertDoesNotThrow(() -> consultarValidadePontos.execute(delegateExecution));
        verify(delegateExecution).setVariable("validadePonto", true);
    }

    @Test
    void testExecute_ComParametrosNull() throws Exception {
        // Arrange
        when(delegateExecution.getVariable("codSolicitante")).thenReturn(null);
        when(delegateExecution.getVariable("solicitante")).thenReturn(null);
        when(delegateExecution.getVariable("qtdePontos")).thenReturn(null);

        // Act & Assert
        assertDoesNotThrow(() -> consultarValidadePontos.execute(delegateExecution));
        verify(delegateExecution).setVariable("validadePonto", true);
    }

    @Test
    void testExecute_ComStringVazia() throws Exception {
        // Arrange
        String codSolicitante = "";
        String solicitante = "";
        Integer qtdePontos = 100;
        
        when(delegateExecution.getVariable("codSolicitante")).thenReturn(codSolicitante);
        when(delegateExecution.getVariable("solicitante")).thenReturn(solicitante);
        when(delegateExecution.getVariable("qtdePontos")).thenReturn(qtdePontos);

        // Act & Assert
        assertDoesNotThrow(() -> consultarValidadePontos.execute(delegateExecution));
        verify(delegateExecution).setVariable("validadePonto", true);
    }
}