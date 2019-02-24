package br.igti.tcc.checafraude.coletor

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper

interface ColetorInterface {

  /* É o método principal e inicial do coletor, ele deve ter em si a annotation @Scheduled
   * para que este coletor execute seu processo em background */
  void coletar()

  /* Permite tomar ações ao final da coleta */
  public void finalizarColeta(JsonNode node)

  /* Permite tomar ações ao final do processamento dos dados - ou seja ao final de tudo */
  public void finalizarProcessamento()

  /* Consulta e monta o objeto JsonNode root do ponto de percorrimento da árvore */
  public JsonNode consultaJson()

  // TODO evoluir para ter um impl com metodo default
  public void registrarErrosColeta(String detalhesErro)

  /* Recebe o JsonNode root e o percorre, executando o processamento, transformação ou armazenamento.
   Após este passo os dados ficam disponíveis para consultas e análises posteriores. */
  public void processarDados(JsonNode meuNoAtual)

  /* Monta um ObjectMapper com parametros para processar o JSON retornado */
  public ObjectMapper montarObjectMapper()
}
