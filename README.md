# cidprojetoaplicado
Projeto de coleta, pré-processamento e análise de dados públicos de compras governamentais, inicialmente usando as APIs Dados.gov.br, ReceitaWS e API CNJ.

Vai lidar com datasets um pouco grandes, então pode ser uma boa idéia aumentar a memória. Usar XMS de 512 mb e XMX de 2048 é um bom ponto de partida.

Tecnologias utilizadas:

- Gradle
- Groovy
- SpringBoot 2.0.6
- SpringCloud (Finchley)
- SpringData
- SpringSecurity
- Jackson
- Tem teste: Spock, Junit, JMeter
- API Dados.gov.br - Contratos
- API Dados.gov.br - Compras sem licitação
- API Dados.gov.br - Licitações
- API CNJ (possivelmente será descartado)

Persistência em:
- MongoDB 4.0.3 (testado em Community Edition)
- ElasticSearch
- MySQL 5+ (testando em MySQL 8 Community Edition) -> scripts sql disponiveis na pasta src/main/resources/sql 

Configurações: no arquivo application.properties se configura

- URLs de acesso aos endpoints de coleta de dados
- configs de acesso ao MySQL e MongoDB
- definir cada dataset onde vai armazenar/persistir dados (setar mysql ou mongodb)

IMPORTAR PROJETO (IDE INTELLIJ)

No IntelliJ, usar o Auto Import Gradle Project, marcar a opçao de usar o gradle wrapper.

Indicar a JDK 8 para o projeto (não foi testado em versões mais novas do JDK).