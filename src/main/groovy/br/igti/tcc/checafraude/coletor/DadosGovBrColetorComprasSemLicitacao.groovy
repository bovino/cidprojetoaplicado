package br.igti.tcc.checafraude.coletor

import br.igti.tcc.checafraude.entidades.CompraSemLicitacaoEntidade
import br.igti.tcc.checafraude.service.CompraSemLicitacaoService
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

//Classe responsavel pela coleta de dados de compras sem licitação na API Dados.gov.br
@Component
class DadosGovBrColetorComprasSemLicitacao extends ColetorBase implements ColetorInterface {

    private static final Logger log = LoggerFactory.getLogger(DadosGovBrColetorComprasSemLicitacao.class)

    @Value('${url.compras.sem.licitacao}')
    private String urlComprasSemLicitacao
    private Integer offsetAtual = 0

    @Autowired
    CompraSemLicitacaoService service

    @Autowired
    JsonNodeUtil jsonNodeUtil

    @Scheduled(fixedRate = 3000L)
     void coletar() {

        log.info("URL: " + this.urlComprasSemLicitacao + this.offsetAtual)

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
            URL myURL = new URL(this.urlComprasSemLicitacao)
            URL endpoint = new URL(myURL, "compras_slicitacao.json?order=asc&order_by=dt_publicacao&offset=" + this.offsetAtual)
            rootNode = mapper.readTree(endpoint)
            JsonNode comprasNode = rootNode.get("_embedded").get("compras")
            println(' OFFSET ATUAL ::: ' + this.offsetAtual)
            println('Compras sem licitação processadas ::: ' + comprasNode.size())
            println('Primeira Compra sem licitação processada ::: ' + comprasNode.get(0).get("nu_aviso_licitacao"))
            println('Última Compra sem licitação processada ::: ' + comprasNode.get(comprasNode.size() - 1).get("nu_aviso_licitacao"))

            //adicionar logica de pré-processamento e armazenamento aqui

            //persistir contrato apenas os campos que importam para análise posterior
            List<CompraSemLicitacaoEntidade> listaCompras = new ArrayList<CompraSemLicitacaoEntidade>()

            for (JsonNode meuNoAtual : comprasNode) {

                CompraSemLicitacaoEntidade entidade = new CompraSemLicitacaoEntidade()
                entidade.codOrgao = Long.valueOf(meuNoAtual.get('co_orgao').textValue())
                entidade.codModalidadeLicitacao = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('co_modalidade_licitacao'))
                entidade.codUasg = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('co_uasg'))
                entidade.numeroProcesso = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('nu_processo'))
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
                entidade.cargoResponsavelRatificacao = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('no_cargo_resp_ratificacao'))

                // service.saveAndFlush(entidade) insert em lote provavelmente mais rapido do que insert um por um
                // (comparar desempenho e quantificar a diferença?)
                listaCompras.add(entidade)
            }

            if (listaCompras.size() > 0) { //insert em lote a cada "pagina" / offset do JSON obtido
             service.saveAll(listaCompras)
            }
        }

        this.offsetAtual += 500
    }

    @Override
    void finalizarColeta() {

    }

    @Override
    void finalizarProcessamento() {

    }

    @Override
    void consultaJson() {

    }

    @Override
    void registrarErrosColeta() {

    }

    @Override
    void processarDados(JsonNode meuNoAtual) {

    }

    @Override
    ObjectMapper montarObjectMapper() {
        return null
    }

}