package br.igti.tcc.checafraude.coletor

import br.igti.tcc.checafraude.entidades.FornecedorEntidade
import br.igti.tcc.checafraude.service.FornecedorService
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

//Classe responsavel pela coleta de fornecedores na API Dados.gov.br
@Component
class DadosGovBrColetorFornecedor extends ColetorBase implements ColetorInterface {

    private static final Logger log = LoggerFactory.getLogger(DadosGovBrColetorFornecedor.class)

    @Value('${url.fornecedores}')
    private String urlFornecedor
    private Integer offsetAtual = 0

    @Autowired
    FornecedorService service

    @Autowired
    JsonNodeUtil jsonNodeUtil

    @Scheduled(fixedRate = 3000L)
     void coletar() {

        log.info(" Coletor de fornecedores")

        log.info("URL: " + this.urlFornecedor + this.offsetAtual)

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
            URL myURL = new URL(this.urlFornecedor)
            URL endpoint = new URL(myURL, "?order=asc&order_by=id&offset=" + this.offsetAtual)
            rootNode = mapper.readTree(endpoint)
            JsonNode fornecedorNode = rootNode.get("_embedded").get("fornecedores")
            println(' OFFSET ATUAL ::: ' + this.offsetAtual)
            println(' Fornecedores processados ::: ' + fornecedorNode.size())
            println(' Primeiro Fornecedor processado ::: ' + fornecedorNode.get(0).get("id"))
            println(' Último Fornecedor processado ::: ' + fornecedorNode.get(fornecedorNode.size() - 1).get("id"))

            //adicionar logica de pré-processamento e armazenamento aqui

            //persistir contrato apenas os campos que importam para análise posterior
            List<FornecedorEntidade> listaFornecedores = new ArrayList<FornecedorEntidade>()

            for (JsonNode meuNoAtual : fornecedorNode) {

                FornecedorEntidade entidade = new FornecedorEntidade()
                entidade.id = meuNoAtual.get('id')
                entidade.cpf = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('cpf'))
                entidade.cnpj = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('cnpj'))
                entidade.ativo = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('ativo'))
                entidade.idCnae = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('id_cnae'))
                entidade.idCnae2 = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('id_cnae2'))
                entidade.idMunicipio = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('id_municipio'))
                entidade.habilitadoLicitar = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('habilitado_licitar'))
                entidade.nome = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('nome'))
                entidade.uf = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('uf'))
                entidade.idNaturezaJuridica = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('id_natureza_juridica'))
                entidade.idPorteEmpresa = jsonNodeUtil.checarNodeNulo(meuNoAtual.get('id_porte_empresa'))

                // TODO completar o set dos demais atributos de fornecedor

                // service.saveAndFlush(entidade) insert em lote provavelmente mais rapido do que insert um por um
                // (comparar desempenho e quantificar a diferença?)
                listaFornecedores.add(entidade)
            }

            if (listaPregoes.size() > 0) { //insert em lote a cada "pagina" / offset do JSON obtido
                service.saveAll(listaFornecedores)
            }
        }

        this.offsetAtual += 500
    }
}