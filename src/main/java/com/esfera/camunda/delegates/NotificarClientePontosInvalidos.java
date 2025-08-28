package com.esfera.camunda.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class NotificarClientePontosInvalidos implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        //Ler Variaveis de Processo
        String solicitante =  (String) delegateExecution.getVariable("solicitante");
        //Lógica da Atividade
        System.out.println(solicitante + " infelizmente seus Pontos estão invalidos!!!" );

    }
}
