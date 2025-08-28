package com.esfera.camunda.delegates;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class CalcularMediaPontosBeneficios implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        //try {
            //Ler Variaveis do Processo
            Integer qtdePontos =  (Integer) delegateExecution.getVariable("qtdePontos");
            Long beneficioLong = (Long) delegateExecution.getVariable("beneficio");
            Integer beneficio = beneficioLong.intValue();

            Integer calculo = qtdePontos/beneficio;

            // Lógica da Atividade
            System.out.println("Calculo: " + calculo);

       // } catch (ArithmeticException exception){

       //     throw new BpmnError("ERRO_CALCULO", "Erro aritmético no cálculo");

    //    }



    }
}
