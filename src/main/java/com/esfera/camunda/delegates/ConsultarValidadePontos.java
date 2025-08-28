package com.esfera.camunda.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ConsultarValidadePontos implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        //Ler Variaveis do Processo
        String codSolicitante =  (String) delegateExecution.getVariable("codSolicitante");
        String solicitante =  (String) delegateExecution.getVariable("solicitante");
        Integer qtdePontos =  (Integer) delegateExecution.getVariable("qtdePontos");

        //Lógica da Atividade
        System.out.println("Solicitante: " + solicitante);
        System.out.println("CodSolicitante: " + codSolicitante);
        System.out.println("QtdePontos Solicitados: " + qtdePontos);

        Random random = new Random();
        // Gera um valor booleano aleatório
        boolean validadePonto = true;//random.nextBoolean();
        System.out.println("Pontos Validos: " + validadePonto);

        //Atualizar Variaveis do Processo
        delegateExecution.setVariable("validadePonto", validadePonto);

    }
}
