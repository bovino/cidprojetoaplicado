package br.igti.tcc.checafraude.coletor

import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.ResponseErrorHandler

class RestTemplateErrorHandler implements ResponseErrorHandler {
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        println('StatusCode :: ' + response.getStatusCode())
        println('Text :: ' + response.getBody().getText())
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().value() == 500 || response.getStatusCode().value() == 404
    }
}