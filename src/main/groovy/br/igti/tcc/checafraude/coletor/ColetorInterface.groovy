package br.igti.tcc.checafraude.coletor

import com.fasterxml.jackson.databind.JsonNode

interface ColetorInterface {

  /* Permite tomar ações ao final da coleta */
  public void finalizarColeta()

  /* Permite tomar ações ao final do processamento dos dados - ou seja ao final de tudo */
  public void finalizarProcessamento()

  /* Consulta e monta o objeto JsonNode root do ponto de percorrimento da árvore */
  public void consultaJson()

  // TODO evoluir para ter um impl com metodo default
  public void registrarErrosColeta()

  /* Recebe o JsonNode root e o percorre, executando o processamento, análise ou armazenamento dos dados para análises e processamentos posteriores */
  public void processarDados(JsonNode meuNoAtual)
}
