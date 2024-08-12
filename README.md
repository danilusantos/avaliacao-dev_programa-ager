# Projeto de Avaliação de Desenvolvedores
Este projeto foi desenvolvido como parte de uma avaliação de desenvolvedores para a empresa ACE OESEIIIVO.Vll,4EIHO Dii SISTE AS LTDA. O projeto foi baseado na estrutura pré-pronta disponibilizada pela empresa.

## Objetivo
O objetivo deste projeto foi simular as atividades realizadas no sistema principal da empresa, o SOC, implementando funcionalidades típicas do dia a dia.

## Funcionalidades Implementadas
#### CRUD de Exames:

- Inclusão de exames.
- Consulta de exames.
- Implementação da funcionalidade de alteração e deleção de exames.

#### CRUD de Funcionários:

- Inclusão, consulta, alteração e deleção de funcionários.

#### CRUD de Exames Realizados por Funcionários:

- Registro de exames realizados, incluindo a data da realização.
- Implementação de restrição para evitar a duplicidade de exames para o mesmo funcionário na mesma data.

#### Melhorias Adicionais:

- Implementação de regra para deletar exames realizados de um funcionário ao deletar o funcionário.
- Implementação de regra para impedir a deleção de exames que já tenham sido realizados por funcionários.

#### Relatório de Exames Realizados:

- Geração de relatório de exames realizados em um período específico.
- Relatório disponível em formato HTML (tela) e XLS/XLSX (Excel).

## Tecnologias Utilizadas
- Java 8
- Struts 2
- JSP e Javascript no front-end
- JDBC para conexão com banco de dados
- Banco de Dados H2
- Apache POI (para geração de arquivos Excel)

Instalação e Configuração
Pré-Requisitos:

- Java 8 instalado.
- Eclipse IDE.
- Git e Git Bash instalados.

1. Clonagem do Repositório.
2. Importar como um projeto Maven existente.
3. Atualização das Dependências: No Eclipse, utilize a opção "Update Project" com Maven para baixar as dependências.

## Inicialização da Aplicação:
No Eclipse, execute a aplicação com o comando Maven jetty:run.

## Acesso à Aplicação
Acesse http://localhost:8080/avaliacao para visualizar a página de consulta de Exames.

## Contato
Em caso de dúvidas, entre em contato pelo e-mail: danilondosantos@gmail.com
