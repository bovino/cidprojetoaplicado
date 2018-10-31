package br.igti.tcc.checafraude.coletor

import br.igti.tcc.checafraude.entidades.ContratoEntidade
import br.igti.tcc.checafraude.service.ContratoService
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
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

    @Autowired
    ContratoService service

    @Scheduled(fixedRate = 3000L)
     void coletar() {

        log.info("URL: " + this.urlContratosBase + this.offsetAtual)

        ObjectMapper mapper = new ObjectMapper()
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        mapper.configure(DeserializationFeature.FAIL_ON_TRAILING_TOKENS, false)
        mapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false)
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
        mapper.configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, false)
        mapper.configure(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS, false)
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true)
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)

        // por enquanto limitar em 10 mil registros, apenas para impl da prova de conceito do orientador
        if(this.offsetAtual < 100000) {

            JsonNode rootNode
            URL myURL = new URL(this.urlContratosBase)
            URL endpoint = new URL(myURL, "contratos.json?order=asc&order_by=data_assinatura&offset=" + this.offsetAtual)
            rootNode = mapper.readTree(endpoint)
            JsonNode contratosNode = rootNode.get("_embedded").get("contratos")
            println(' OFFSET ATUAL ::: ' + this.offsetAtual)
            println('Contratos processados ::: ' + contratosNode.size())
            println('Primeiro Contrato processado ::: ' + contratosNode.get(0).get("identificador"))
            println('Último Contrato processado ::: ' + contratosNode.get(contratosNode.size() - 1).get("identificador"))

            //adicionar logica de pré-processamento e armazenamento aqui

            //persistir contrato apenas os campos que importam para análise posterior
            List<ContratoEntidade> listaContratos = new ArrayList<ContratoEntidade>()

            for (JsonNode meuNoAtual : contratosNode) {

                ContratoEntidade entidade = new ContratoEntidade()
                entidade.identificador = Long.valueOf(meuNoAtual.get('identificador').textValue())
                entidade.objeto = checarNodeNulo(meuNoAtual.get('objeto'))
                entidade.uasg = checarNodeNulo(meuNoAtual.get('uasg'))
                entidade.valorInicial = checarNodeNulo(meuNoAtual.get('valor_inicial'))
                entidade.numeroAditivo = checarNodeNulo(meuNoAtual.get('numero_aditivo'))
                entidade.cnpjContratada = checarNodeNulo(meuNoAtual.get('cnpj_contratada'))

                entidade.numero = checarNodeNulo(meuNoAtual.get('numero'))
                entidade.numeroAditivo = checarNodeNulo(meuNoAtual.get('numero_aditivo'))
                entidade.codigoContrato = checarNodeNulo(meuNoAtual.get('codigo_contrato'))
                entidade.dataAssinatura = checarNodeNulo(meuNoAtual.get('data_assinatura'))
                entidade.dataInicioVigencia = checarNodeNulo(meuNoAtual.get('data_inicio_vigencia'))
                entidade.dataTerminoVigencia = checarNodeNulo(meuNoAtual.get('data_termino_vigencia'))
                entidade.modalidadeLicitacao = checarNodeNulo(meuNoAtual.get('modalidade_licitacao'))
                entidade.licitacaoAssociada = checarNodeNulo(meuNoAtual.get('licitacao_associada'))
                entidade.fundamentoLegal = checarNodeNulo(meuNoAtual.get('fundamento_legal'))

                // service.saveAndFlush(entidade) insert em lote provavelmente mais rapido do que insert um por um
                // (comparar desempenho e quantificar a diferença?)
                listaContratos.add(entidade)
            }

            if (listaContratos.size() > 0) { //insert em lote a cada "pagina" / offset do JSON obtido
             service.saveAll(listaContratos)
            }
        }

        this.offsetAtual += 500
    }

    private String checarNodeNulo(JsonNode no){
        if( no == null ){
            return ''
        } else if(no.isTextual()){ // nao é nulo mas é nó textual
            return no.textValue() != null ? no.textValue() : ''
        } else { // nao é nulo e NAO é um nó textual
            return no.asText() != null ? no.asText() : ''
        }
    }
}