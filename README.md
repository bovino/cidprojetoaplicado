# cidprojetoaplicado
Projeto de coleta, pré-processamento e análise de dados públicos de compras governamentais, inicialmente usando as APIs públicas Dados.gov.br e CNJ

Vai lidar com datasets um pouco grandes, então pode ser uma boa idéia aumentar a memória. Usar XMS de 512 mb e XMX de 2048 é um bom ponto de partida.

Tecnologias utilizadas:

- Gradle
- Groovy
- SpringBoot 2.0.4
- SpringCloud
- SpringData
- SpringSecurity
- Jackson
- Tem teste: Spock, Junit, JMeter
- API Dados.gov.br
- API CNJ

No IntelliJ, usar o Auto Import Gradle Project, marcar a opçao de usar o gradle wrapper.

Indicar a JDK 8 para o projeto (não foi testado em versões mais novas do JDK).