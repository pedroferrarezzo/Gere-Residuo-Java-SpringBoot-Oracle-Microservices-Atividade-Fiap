# ATIVIDADE
**Tema escolhido:** Gestão de Resíduos - Notificação aos moradores sobre dias de coleta e separação adequada de resíduos.

"Pedimos a você e a seu grupo que reflitam o tema escolhido, e qual o mínimo para criar um produto viável do tema selecionado. Com isso, a sua tarefa consiste em implementar uma série (mínimo 4) endpoints RESTful que ofereçam funcionalidades relevantes para o projeto. Revisitando o exemplo da coleta seletiva de lixo, você poderia considerar endpoints como:

- GET /coleta-de-lixo: Para recuperar informações sobre a coleta de lixo;
- POST /agendamento-de-coleta: Para agendar uma nova coleta;
- PUT /agendamento-de-coleta/{id}: Para atualizar um agendamento existente;
- DELETE /agendamento-de-coleta/{id}: Para cancelar um agendamento de coleta.

Com o seu aprofundamento nos conceitos de desenvolvimento com Java e Spring Boot, é mandatório que você sugira e implemente mais itens na entrega, indo além dos endpoints. Desde a configuração inicial até técnicas avançadas de validação e tratamento de exceções, é essencial aplicar os requisitos de segurança nos endpoints pertinentes utilizando o Spring Security.

Além disso, é necessário que a solução esteja integrada ao banco de dados, especialmente no caso do Oracle, e que o conceito de migrações seja implementado para garantir uma gestão eficiente e escalável do esquema de banco de dados ao longo do tempo. Explore também a integração com contêineres Docker, entregando as configurações para execução do projeto estejam devidamente documentadas e prontas para implantação em ambientes diversos."

# TECNOLOGIAS UTILIZADAS
- Java 21;
- Spring Framework;
  - Spring Data JPA;
  - Spring Web MVC;
  - Spring Security.
- Hibernate ORM;
- Maven;
- Service Discovery - Netflix Eureka;
- API Gateway - Spring Cloud Gateway.

# BOAS PRÁTICAS E DESIGN PATTERNS UTILIZADOS
- Data Transfer Objects - DTO;
- Services;
- Repositories;
- MVC - Model, View e Controller;
- Global Exception Handler;
- Exceptions customizadas de acordo com as regras de negócio.

# MER
![image](https://github.com/pedroferrarezzo/Gere-Residuo-Java-SpringBoot-Oracle-Atividade-Fiap/assets/124400471/5e487da1-c3e1-4c64-af08-ff58bb646ace)

# VISÃO GERAL - ENDPOINTS E MICROSSERVIÇOS
![image](https://github.com/pedroferrarezzo/Gere-Residuo-Java-SpringBoot-Oracle-Atividade-Fiap/assets/124400471/d32f2da3-99e9-4dbe-a69c-2656e048a2f5)

# INTEGRAÇÃO - SERVIDOR SMTP
Para de fato atender a ideia de “notificação aos moradores” do tema escolhido, o endpoint “disparar notificações” faz uso de um servidor de SMTP gratuito chamado MailTrap. Ao acionar o endpoint passando o id de uma agenda aberta, um email será enviado para cada registro da tabela T_NOTIFICACAO referente ao id da agenda, em seguida o registro é excluído. Como boa prática, o método de envio de e-mail foi marcado como @Async.
![image](https://github.com/pedroferrarezzo/Gere-Residuo-Java-SpringBoot-Oracle-Atividade-Fiap/assets/124400471/ed3191df-250e-4404-aa41-117dbef348ae)
  
# DETALHES DE IMPLEMENTAÇÃO - SPRING SECURITY
- O serviço escolhido para implementação do Spring Security foi o API Gateway (visto que é ele quem intercepta todas as requisições);
- Ao decorrer da fase, foi ensinado a implementação do Spring Security com Spring Web MVC. Levando em consideração que o API Gateway utiliza Spring WebFlux, o desenvolvimento se deu baseado em artigos e documentações oficias (Sujeito a melhorias);
- <p>Lógica da Security Chain;</p>

  ![image](https://github.com/pedroferrarezzo/Gere-Residuo-Java-SpringBoot-Oracle-Atividade-Fiap/assets/124400471/e2a83ab7-29e4-4bed-a184-b2ab5a269e6c)
  
- <p>Anatomia de um Token JWT gerado;</p>

  ![image](https://github.com/pedroferrarezzo/Gere-Residuo-Java-SpringBoot-Oracle-Atividade-Fiap/assets/124400471/4bb39987-ca60-4ad2-93d5-e8fec98d7db0)
  
- A secret JWT deve ter mais de 256 bits, caso contrário, uma exception será lançada.

# DETALHES DE IMPLEMENTAÇÃO - DOCKER
- A API foi totalmente conteinerizada:
  - Integrando-a com um banco de dados OracleXE em container e outros disponibilizados por terceiros no Docker Hub;
  - Aplicando o conceito de variáveis de ambiente (para a secret JWT);
  - Construindo todas as imagens e subindo-as para um repositório no Docker Hub (ferrarezzodev/fiap - https://hub.docker.com/repository/docker/ferrarezzodev/fiap/general);
  - Construindo um docker-compose.yml para a fácil inicialização de toda a API, consumindo as imagens dos microsserviços hospedadas no Docker Hub.
- <p>Tempo médio para inicialização de toda a API usando o docker-compose.yml (teste feito em uma VM no Azure);</p>

  ![image](https://github.com/pedroferrarezzo/Gere-Residuo-Java-SpringBoot-Oracle-Atividade-Fiap/assets/124400471/bd05c9da-6d94-4edd-bf3c-b553f153c4cf)

    - VM utilizada para os testes: Standard D2s v3 (2 vcpus, 8 GiB memory) - Ubuntu 20.04.6 LTS;
    - oracledb – uma validação de “healthy” foi aplicada utilizando um script shell disponibilizado pela própria imagem para validar que de fato o banco está ON;
    - <p>oracleclient – imagem com o sqlplus instalado, utilizada para validar se as tabelas T_MOTORISTA, T_CAMINHAO, T_AGENDA e T_MORADOR já foram efetivamente criadas pelo flyway, nos microsserviços morador.bairro.ms e motorista.caminhao.ms. Isto é necessário          pois o flyway no microsserviço agenda.notificacao.ms irá criar chaves estrangeiras com essas tabelas – que inclusive estão em schemas diferentes. Para tal, verificar se o script abaixo possui permissões de execução o suficiente para que o docker engine possa       usá-lo;</p>
    
      ![image](https://github.com/pedroferrarezzo/Gere-Residuo-Java-SpringBoot-Oracle-Atividade-Fiap/assets/124400471/934caa35-bafd-46ef-a5b8-090755a6dc8e)
      
    - Uma rede exclusiva foi criada para todas as APIs;
    - Um volume foi criado para persistência do diretório ORADATA (Oracle).
- <p>Mapeamento de portas;</p>

  ![image](https://github.com/pedroferrarezzo/Gere-Residuo-Java-SpringBoot-Oracle-Atividade-Fiap/assets/124400471/ec6182af-30f8-49e7-9fda-95e8f46293a6)

- Todas as imagens necessárias já se encontram no Docker Hub, mas caso seja necessário buildar localmente, o seguinte comando deve ser executado dentro do diretório “docker/dockerfiles” (gerar os .jar e colocar dentro do diretório “docker/jar”): docker buildx build -f docker_file -t account_name/repository:tagname .
- Todos os microsserviços se conectam ao banco de dados e ao eureka via nome de domínio (respectivamente “oracledb” e “eureca”). Estes nomes são resolvidos pelo serviço de DNS interno do docker.

# INFORMAÇÔES FINAIS
- Todos os arquivos utilizados para conteinerizar a API se encontram na pasta “docker”;
- Todo o código fonte da API se encontra na pasta “java”;
- Os arquivos SQL DDL utilizados pelo flyway se encontram nos respectivos diretórios de cada projeto de cada microsserviço (db.migration).

 


