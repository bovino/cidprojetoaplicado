package br.igti.tcc.checafraude.alerta

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class GerarAlerta {

    @Scheduled(fixedRate = 30000L)
    public void gerarAlerta(){

        // pesquisar no banco os alertas cadastrados

        // checa se algum dos alertas cadastrados tem dados que combina

        // se houver, checar se ja foi enviado alerta para o usuario sobre esses mesmos dados

        // se foi, nao precisa reenviar o mesmo alerta

        // mas se ainda nao foi enviado, obtem os dados , monta um texto e envia o alerta ao destinatario

    }
}
