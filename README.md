# Não se esqueçam de atualizar o Trello

[Trello](https://trello.com/b/xyExlXkg/projeto-a3)

## Atualização do Projeto

O projeto agora foi transferido para usar o Maven. O Maven é uma ferramenta de automação de construção que facilita a gestão de dependências e a construção do projeto.

## Instalação do Maven

Para instalar o Maven, siga os passos abaixo:

1. Baixe a versão mais recente do Maven a partir do [site oficial do Maven](https://maven.apache.org/download.cgi).
2. Extraia o arquivo baixado para um diretório de sua escolha.
3. Adicione o diretório `bin` do Maven ao seu `PATH`. No Windows, você pode fazer isso através das Propriedades do Sistema -> Variáveis de Ambiente. No Linux e no Mac, você pode adicionar a seguinte linha ao seu arquivo `.bashrc` ou `.bash_profile`: `export PATH=/caminho/para/maven/bin:$PATH`
4. Verifique se o Maven foi instalado corretamente abrindo um novo terminal e executando o comando `mvn --version`. Você deve ver a versão do Maven que você instalou.

## Executando o Projeto com Maven

Para executar o projeto com Maven, siga os passos abaixo:

1. Abra um terminal e navegue até o diretório raiz do projeto (o diretório que contém o arquivo `pom.xml`).
2. Execute o comando `mvn clean install` para limpar qualquer construção anterior e baixar todas as dependências necessárias.
3. Após a construção bem-sucedida, você pode executar o projeto diretamente com o comando `java`, mas é melhor vocês o executarem pela sua IDE

