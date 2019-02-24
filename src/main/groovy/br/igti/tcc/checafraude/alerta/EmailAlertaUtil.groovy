package br.igti.tcc.checafraude.alerta

import com.fasterxml.jackson.databind.JsonNode
import org.springframework.stereotype.Component

@Component
class EmailAlertaUtil {

    // receba texto de alerta e o envia para usuário destinatario usando o metodo de envio selecionado
    void enviarEmailAlterta(String texto, String destinatario, String metodoEnvio){
    }

    // monta um texto de alerta para ser enviado a usuario especifico
    void montarEmailAlerta(List<String> nomesTiposEntidadesEnvolvidas,
                           List<String> nomesEntidadesEnvolvidas,
                           List<Long> identificadores,
                           String destinatario){


    }
}
