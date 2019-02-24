package br.igti.tcc.checafraude.coletor

import br.igti.tcc.checafraude.entidades.GrupoMaterialEntidade
import br.igti.tcc.checafraude.service.GrupoMaterialService
import br.igti.tcc.checafraude.util.JsonNodeUtil
import br.igti.tcc.checafraude.util.ObjectMapperUtil
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

//Classe responsavel pela coleta de grupos de materiais na API Dados.gov.br
@Component
class DadosGovBrColetorGrupoMaterial extends ColetorBase implements ColetorInterface {

    private static final Logger log = LoggerFactory.getLogger(DadosGovBrColetorGrupoMaterial.class)

    @Value('${url.materiais.grupo}')
    private String urlGrupo
    private Integer offsetAtual = 0

    @Autowired
    GrupoMaterialService service

    @Autowired
    JsonNodeUtil jsonNodeUtil

    @Scheduled(fixedRate = 3000L)
    void coletar() {

        log.info(" Coletor de grupos de materiais")
        log.info("URL: " + this.urlGrupo + this.offsetAtual)

        // por enquanto limitar em 10 mil registros, apenas para impl da prova de conceito do orientador
        if(this.offsetAtual < 100000) {
            JsonNode grupoMaterialNode = this.consultaJson()
            this.finalizarColeta(grupoMaterialNode)
            this.processarDados(grupoMaterialNode)
            this.finalizarProcessamento()
        }

        this.offsetAtual += 500
    }

    @Override
    void finalizarColeta( JsonNode node ) {
        println('COLETA DE GRUPOS DE MATERIAIS FINALIZADA')
        println(' OFFSET ATUAL ::: ' + this.offsetAtual)
        println(' Grupos de materiais processados ::: ' + node.size())
        println(' Primeiro Grupo de material processado ::: ' + node.get(0).get("numero"))
        println(' Último grupo de material processado ::: ' + node.get(node.size() - 1).get("numero"))
    }

    @Override
    void finalizarProcessamento() {
        println('PROCESSAMENTO DE GRUPOS DE MATERIAIS FINALIZADA')
    }

    @Override
    JsonNode consultaJson() {
        JsonNode rootNode
        URL myURL = new URL(this.urlGrupo)
        URL endpoint = new URL(myURL, "?order=asc&order_by=codigo&offset=" + this.offsetAtual)
        rootNode = montarObjectMapper().readTree(endpoint)
        JsonNode grupoMaterialNode = rootNode.get("_embedded").get("grupos")
        return grupoMaterialNode
    }

    @Override
    void registrarErrosColeta(String detalhesErro) {
        // registrar erros ocorridos no MongoDB
    }

    @Override
    void processarDados(JsonNode jsonNodeParaPercorrer) {

        // transforma o JSONNode em entidade JPA e depois persiste
        //persistir apenas os campos que importam para análise posterior
        List<GrupoMaterialEntidade> listaGruposMateriais = new ArrayList<GrupoMaterialEntidade>()

        for (JsonNode meuNoAtual : jsonNodeParaPercorrer) {

            try {

                GrupoMaterialEntidade entidade = new GrupoMaterialEntidade()
                entidade.codigo = meuNoAtual.get('codigo')
                entidade.descricao = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('descricao'))

                // service.saveAndFlush(entidade) insert em lote provavelmente mais rapido do que insert um por um
                // (comparar desempenho e quantificar a diferença?)
                listaGruposMateriais.add(entidade)
            }

            catch( Exception ex ){
              this.registrarErrosColeta( this.getClass().getCanonicalName() + ' - ' + ex.getMessage())
            }
        }

        if (listaGruposMateriais.size() > 0) {
            //insert em lote a cada "pagina" / offset do JSON obtido
            // talvez depois alterar para permitir registrar erro em registro especifico
            // porem pode ficar um pouco mais lento do que salvar em lote
            try {
                service.saveAll(listaGruposMateriais)
            } catch (Exception ex){
                this.registrarErrosColeta( this.getClass().getCanonicalName() + ' - ' + ex.getMessage())
            }
        }
    }

    @Override
    ObjectMapper montarObjectMapper() {
        return ObjectMapperUtil.getDefaultObjectMapper()
    }
}