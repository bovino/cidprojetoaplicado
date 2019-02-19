package br.igti.tcc.checafraude.coletor

import br.igti.tcc.checafraude.entidades.PregaoEntidade
import br.igti.tcc.checafraude.service.PregaoService
import br.igti.tcc.checafraude.util.JsonNodeUtil
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

//Classe responsavel pela coleta de pregoes na API Dados.gov.br
@Component
class DadosGovBrColetorPregao extends ColetorBase implements ColetorInterface {

    private static final Logger log = LoggerFactory.getLogger(DadosGovBrColetorPregao.class)

    @Value('${url.pregoes}')
    private String urlPregao
    private Integer offsetAtual = 0

    @Autowired
    PregaoService service

    @Autowired
    JsonNodeUtil jsonNodeUtil

    @Scheduled(fixedRate = 3000L)
     void coletar() {

        log.info(" Coletor de pregoes")

        log.info("URL: " + this.urlPregao + this.offsetAtual)

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
            URL myURL = new URL(this.urlPregao)
            URL endpoint = new URL(myURL, "?order=asc&order_by=dt_data_edital&offset=" + this.offsetAtual)
            rootNode = mapper.readTree(endpoint)
            JsonNode pregoesNode = rootNode.get("_embedded").get("pregoes")
            println(' OFFSET ATUAL ::: ' + this.offsetAtual)
            println(' Pregões processados ::: ' + pregoesNode.size())
            println(' Primeiro Pregão processado ::: ' + pregoesNode.get(0).get("numero"))
            println(' Último pregão processado ::: ' + pregoesNode.get(pregoesNode.size() - 1).get("numero"))

            //adicionar logica de pré-processamento e armazenamento aqui

            //persistir contrato apenas os campos que importam para análise posterior
            List<PregaoEntidade> listaPregoes = new ArrayList<PregaoEntidade>()

            for (JsonNode meuNoAtual : pregoesNode) {

                PregaoEntidade entidade = new PregaoEntidade()
                entidade.numero = meuNoAtual.get('numero')
                entidade.codigoProcesso = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('co_processo'))
                entidade.codigoPortaria = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('co_portaria'))
                entidade.situacaoPregao = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('ds_situacao_pregao'))
                entidade.tipoPregao = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('ds_tipo_pregao'))
                entidade.tipoPregaoCompra = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('ds_tipo_pregao_compra'))
                entidade.textoObjeto = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('tx_objeto'))

                /* entidade.numeroProcesso = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('nu_processo'))
                entidade.valorEstimado = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('vr_estimado'))
                entidade.objetoLicitacao = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('ds_objeto_licitacao'))
                entidade.fundamentoLegal = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('ds_fundamento_legal'))
                entidade.justificativa = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('ds_justificativa'))
                entidade.dtRatificacao = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('dtRatificacao'))
                entidade.dtPublicacao = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('dtPublicacao'))
                entidade.dtDeclaracaoDispensa = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('dtDeclaracaoDispensa'))
                entidade.nomeResponsavelDeclaracaoDispensa = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('no_responsavel_decl_disp'))
                entidade.cargoResponsavelDeclaracaoDispensa = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('no_cargo_resp_decl_disp'))
                entidade.nomeResponsavelRatificacao = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('no_responsavel_ratificacao'))
                entidade.cargoResponsavelRatificacao = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('no_cargo_resp_ratificacao')) */

                // service.saveAndFlush(entidade) insert em lote provavelmente mais rapido do que insert um por um
                // (comparar desempenho e quantificar a diferença?)
                listaPregoes.add(entidade)
            }

            if (listaPregoes.size() > 0) { //insert em lote a cada "pagina" / offset do JSON obtido
                service.saveAll(listaPregoes)
            }
        }

        this.offsetAtual += 500
    }
}