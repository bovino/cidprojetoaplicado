package br.igti.tcc.checafraude.coletor

// import br.igti.tcc.checafraude.entidades.PregaoEntidade
import br.igti.tcc.checafraude.service.ModalidadeLicitacaoService
import br.igti.tcc.checafraude.util.JsonNodeUtil
// import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonNode
// import com.fasterxml.jackson.databind.ObjectMapper
// import com.fasterxml.jackson.databind.SerializationFeature
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
// import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

//Classe responsavel pela coleta de modalidades de licitação na API Dados.gov.br
@Component
class DadosGovBrColetorModalidadeLIcitacao extends ColetorBase implements ColetorInterface {

    private static final Logger log = LoggerFactory.getLogger(DadosGovBrColetorModalidadeLIcitacao.class)

    @Autowired
    ModalidadeLicitacaoService service

    @Autowired
    JsonNodeUtil jsonNodeUtil

    @Scheduled(fixedRate = 3000L)
     void coletar() {

        // TODO implementar a coleta na API
        log.info(" Coletor de modalidade de licitacao")

    }

}