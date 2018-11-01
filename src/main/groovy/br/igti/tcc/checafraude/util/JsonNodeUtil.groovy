package br.igti.tcc.checafraude.util

import com.fasterxml.jackson.databind.JsonNode
import org.springframework.stereotype.Component

@Component
class JsonNodeUtil {

    String checarNodeNulo(JsonNode no){
        if( no == null ){
            return ''
        } else if(no.isTextual()){ // nao é nulo mas é nó textual
            return no.textValue() != null ? no.textValue() : ''
        } else { // nao é nulo e NAO é um nó textual
            return no.asText() != null ? no.asText() : ''
        }
    }
}
