package br.igti.tcc.checafraude.coletor

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

//Classe responsavel pela coleta de dados de contratos na API Dados.gov.br
@Component
class DadosGovBrColetorContratos implements ColetorInterface {

    private static final Logger log = LoggerFactory.getLogger(DadosGovBrColetorContratos.class)

    @Value('${url.contratos.base}')
    private String urlContratosBase

    private Integer offsetAtual = 0

    @Scheduled(fixedRate = 50000L)
     void coletar() {

        log.info("URL: " + this.urlContratos + this.offsetAtual)

        ObjectMapper mapper = new ObjectMapper()
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        mapper.configure(DeserializationFeature.FAIL_ON_TRAILING_TOKENS, false)
        mapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false)
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
        mapper.configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, false)
        mapper.configure(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS, false)
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true)
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)

        JsonNode rootNode
        URL myURL = new URL(this.urlContratosBase)
        URL endpoint = new URL(myURL, "contratos.json?order=asc&order_by=data_assinatura&offset=" + this.offsetAtual)
        rootNode = mapper.readTree(endpoint)
        JsonNode contratosNode = rootNode.get("_embedded").get("contratos")
        println(' OFFSET ATUAL ::: ' + this.offsetAtual)
        println('Contratos processados ::: ' + contratosNode.size())
        println('Primeiro Contrato processado ::: ' + contratosNode.get(0).get("identificador"))
        println('Último Contrato processado ::: ' + contratosNode.get(contratosNode.size()-1).get("identificador"))

        //adicionar logica de pré-processamento e armazenamento aqui

        this.offsetAtual += 500
    }
}