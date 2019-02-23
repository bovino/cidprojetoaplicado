package br.igti.tcc.checafraude.coletor

import br.igti.tcc.checafraude.entidades.ModalidadeLicitacaoEntidade
import br.igti.tcc.checafraude.service.ModalidadeLicitacaoService
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

//Classe responsavel pela coleta de modalidades de licitação na API Dados.gov.br
@Component
class DadosGovBrColetorModalidadeLIcitacao extends ColetorBase implements ColetorInterface {

    private static final Logger log = LoggerFactory.getLogger(DadosGovBrColetorModalidadeLIcitacao.class)

    @Value('${url.modalidades.licitacao}')
    private String urlModalidadeLicitacao
    private Integer offsetAtual = 0

    @Autowired
    ModalidadeLicitacaoService service

    @Autowired
    JsonNodeUtil jsonNodeUtil

    @Scheduled(fixedRate = 300000L)
     void coletar() {

        log.info(" Coletor de modalidade de licitacao")

        log.info("URL: " + this.urlModalidadeLicitacao + this.offsetAtual)

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
            URL myURL = new URL(this.urlModalidadeLicitacao)
            URL endpoint = new URL(myURL, "?order=asc&order_by=codigo&offset=" + this.offsetAtual)
            rootNode = mapper.readTree(endpoint)
            JsonNode modalidadesNode = rootNode.get("_embedded").get("modalidades")
            println(' OFFSET ATUAL ::: ' + this.offsetAtual)
            println(' Modalidade de licitação processadas ::: ' + modalidadesNode.size())
            println(' Primeira Modalidade de licitação processada ::: ' + modalidadesNode.get(0).get("codigo"))
            println(' Última Modalidade de licitação processada ::: ' + modalidadesNode.get(modalidadesNode.size() - 1).get("codigo"))

            //adicionar logica de pré-processamento e armazenamento aqui

            //persistir contrato apenas os campos que importam para análise posterior
            List<ModalidadeLicitacaoEntidade> listaModalidades = new ArrayList<ModalidadeLicitacaoEntidade>()

            for (JsonNode meuNoAtual : modalidadesNode) {

                ModalidadeLicitacaoEntidade entidade = new ModalidadeLicitacaoEntidade()
                entidade.codigo = meuNoAtual.get('codigo')
                entidade.descricao = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('descricao'))

                // service.saveAndFlush(entidade) insert em lote provavelmente mais rapido do que insert um por um
                // (comparar desempenho e quantificar a diferença?)
                listaModalidades.add(entidade)
            }

            if (listaModalidades.size() > 0) { //insert em lote a cada "pagina" / offset do JSON obtido
                service.saveAll(listaModalidades)
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