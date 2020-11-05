## Autorizador de conta

### Introdução

Olá, meu nome é Anderson e a minha proposta é criar uma autorizador de compra seguindo modelo do documento que eu recebi.
Dividi este documento em 4 sessões para explicar quais as tecnologias utilizadas, design e arquitetura do projeto e como executa-lo. 

### Design e arquitetura do projeto

Para este projeto utilizei o modelo em camadas, podendo evolui-la para um modelo de microserviços (Rest ou Fila) ou até um modelo porta e adaptadores.

Organizei as camadas em 3 diretórios

- Application: responsavel pela manipulação dos dados de entrada e saída;
- Domain: responsavel pelo componentes com as regras de negócio.
- Infrastructure: responsavel pelos componentes de acesso ao banco de dados ou filas.

Para integração entre as camadas pensei em alguns designs usados no mundo Java, como inversão de controle, fabricas para crição de objetos, estratégia para casos aplicar comportamentos diferentes para ações como criação de conta ou autorizar tranção.

Meu pensamento sempre foi construir um código flexivel a mudanças e que não dependence de outras camadas para realizar os testes.

Princípios como SOLID, DRY e KISS foram os meus guias para pensar na criação dessa solução.

### Tecnologias

- Linguagem Java - JDK v.11;
- Maven para gerenciar dependências;
- Docker (usar os comandos para não depender da instação na máquina).

### Dependências

Para este projeto use duas bibliotecas importantes para esta solução:

- Gson: uma bibliote que realiza conversão de um json para um objet e o inverso também;
- Junit 5: uma bibliote para construir teste de unidade.

### Como executar os testes e realizar o build

Acessar a pasta do Aplicativo: 

```shell script
cd authorizer
```

Para executar os testes digite o comando abaixo:

```shell script
 docker run --rm -v $PWD:/app -w /app maven:3.6.3-jdk-11 mvn clean test 
```

Para realizar o build do projeto:

```shell script
 docker run --rm -v $PWD:/app -w /app maven:3.6.3-jdk-11 mvn clean package 
```

***Observação***: Infelizmente e até pelo tempo não consegui fazer uma forma que antes da execução do testes ou relizar o buil não fique realizando o download das dependências do maven.

Para realizar o build do projeto:

```shell script
 docker run --rm -v $PWD:/app -w /app openjdk:11 java -jar ./target/authorizer-1.0-SNAPSHOT.jar ./operations 
```

***Observação***: Para esta versão é importante incluir o arquivo com jsons na raiz do projeto antes de realizar o fluxo, pode incluir várias e execuntando o comando mudando somente o nome do arquivo.
