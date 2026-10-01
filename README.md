# Sistema de Supermercado — Cálculo de Preço de Venda

Aplicação desktop em Java para cadastro de produtos e categorias, cálculo do preço de venda a partir da margem de lucro de cada categoria e consulta do histórico de preços.

Projeto desenvolvido na disciplina ministrada pelo Prof. Dr. Clayton Vieira Fraga Filho — Departamento de Computação, UFES.

---

## Tecnologias

- **Java 21**
- **Maven**
- **Swing**, com as telas criadas no editor visual do **NetBeans** (cada tela tem o seu arquivo `.form`)
- Padrão de arquitetura **MVP Passive View** (Model-View-Presenter)
- Dados mantidos **em memória**: não há banco de dados

---

## Como executar

### Pré-requisitos

- JDK 21 ou superior
- Apache NetBeans (recomendado) ou Maven instalado

### Pelo NetBeans

1. Clone o repositório:
   ```bash
   git clone https://github.com/marlondcury/trabalhoProjetos.git
   ```
2. No NetBeans: **File → Open Project** e selecione a pasta clonada.
3. Execute com **Run Project** (F6).

### Pelo terminal

```bash
git clone https://github.com/marlondcury/trabalhoProjetos.git
cd trabalhoProjetos
mvn compile exec:java
```

A classe principal é `app.UmCasoDeUso`, configurada no `pom.xml` (`exec.mainClass`).

Ao iniciar, o **Seeder** carrega os dados de demonstração e, em seguida, a tela principal é aberta. As alterações feitas durante a execução são descartadas ao fechar a aplicação.

---

## Funcionalidades

| Tela | Acesso | O que faz |
|---|---|---|
| **Tela principal** | ao iniciar | Menu **Dados** com acesso às demais telas |
| **Buscar produtos** | Dados → Buscar produtos | Busca por nome do produto ou por categoria (busca parcial, sem diferenciar maiúsculas) |
| **Produto — inclusão/edição** | Dados → Incluir produtos; Novo (busca); Editar (visualização) | Mesma tela nos dois modos; margem e preço de venda não são editáveis |
| **Produto — visualização** | Visualizar (busca) | Consulta do produto, com acesso à edição e ao histórico |
| **Histórico de preços** | Visualizar histórico de preços | Registros de cálculo do produto, do mais recente para o mais antigo |
| **Categorias** | Dados → Categorias | Cadastro mestre-detalhe com modos de visualização, inclusão e edição |
| **Cálculo de margem de lucro** | Dados → Calcular margem de lucro | Cálculo global do preço de venda de todos os produtos |

---

## Regras de negócio

- **Preço de venda** = preço de custo × (1 + percentual de lucro da categoria / 100), arredondado para duas casas decimais.
- O **cálculo global** processa todos os produtos, atualiza a margem e o preço de venda de cada um e grava um registro de histórico por produto.
- Um novo cálculo só pode ser executado **10 dias ou mais** após o cálculo global anterior.
- O **histórico é imutável**: alterar o percentual de uma categoria não modifica registros antigos nem recalcula os produtos. O novo percentual vale a partir do próximo cálculo.
- A **margem e o preço de venda** de um produto só mudam pelo cálculo, nunca por edição direta.
- Uma **categoria com produtos associados** não pode ser excluída.
- Validações:
  - nome do produto obrigatório e não composto apenas por espaços;
  - preço de custo obrigatório e maior que zero;
  - categoria do produto obrigatória e existente no repositório;
  - nome da categoria obrigatório e único, sem diferenciar maiúsculas e minúsculas;
  - percentual de lucro obrigatório e maior ou igual a zero;
  - data do cálculo obrigatória.
- **Operações inválidas** exibem uma mensagem ao usuário e não alteram os dados em memória.

---

## Arquitetura

O projeto segue o **MVP Passive View**: a view apenas exibe componentes, sem nenhuma lógica. O presenter registra os eventos, lê os campos, chama os services e atualiza a tela. As regras de negócio ficam nos services.

```
Usuário ──► View ──► Presenter ──► Service ──► Repository ──► Model
             ▲          │
             └──────────┘
          (o presenter atualiza a view)
```

### Pacotes

| Pacote | Responsabilidade |
|---|---|
| `model` | Classes de domínio: `Categoria`, `Produto`, `HistoricoPreco` |
| `repository` | Contratos (`ICategoriaRepository`, `IProdutoRepository`, `IHistoricoPrecoRepository`) e implementações em memória |
| `service` | Regras de negócio e validações: `CategoriaService`, `ProdutoService`, `CalculoProdutoService`, `HistoricoPrecoService` |
| `seeder` | Carga dos dados iniciais a cada execução |
| `view` | Telas Swing (`JFrame`/`JDialog`) criadas no editor visual do NetBeans |
| `presenter` | Ligação entre as telas e os services |
| `app` | Ponto de entrada: monta repositórios, services, executa o Seeder e abre a tela principal |

### Decisões de projeto

- **Repositórios com interface.** Os services dependem dos contratos, e não das implementações em memória. Trocar a memória por um banco de dados exige apenas uma nova implementação.
- **Injeção de dependência pelo construtor.** Cada repositório é criado uma única vez e compartilhado por todos os services, garantindo que todos enxerguem os mesmos dados.
- **Histórico como "fotografia".** `HistoricoPreco` copia o percentual e o preço no momento do cálculo, em atributos `final`, sem setters.
- **Validar antes de alterar.** Nas edições, os valores novos são validados antes de qualquer alteração no objeto, para que uma operação inválida não deixe dados pela metade.
- **Exceções.** Os services lançam `IllegalArgumentException` (dado inválido) e `IllegalStateException` (operação não permitida no estado atual). Os presenters capturam e exibem a mensagem.

---

## Dados iniciais (Seeder)

A cada execução, são carregadas:

- **7 categorias**: Educação, Papelaria, Alimentação, Lazer, Entretenimento, Higiene e Limpeza;
- **21 produtos**, distribuídos entre as categorias;
- **um registro de histórico por produto**, com a data de 10 dias atrás. Assim, o sistema já inicia com histórico disponível e permite um novo cálculo na data corrente.

---

## Estrutura de pastas

```
src/main/java/
├── app/          UmCasoDeUso.java
├── model/        Categoria, Produto, HistoricoPreco
├── repository/   interfaces e implementações em memória
├── service/      regras de negócio
├── seeder/       Seeder
├── view/         telas (.java + .form)
└── presenter/    presenters
```

---

## Próximos passos

Em desenvolvimento: **Solicitação de Mudança #1 — POC Delivery**, que acrescenta autenticação de usuários, uma nova tela principal e os cadastros de clientes e de usuários.

---

## Autores

- Marlon Domingos
- Sthefane Almeida
- Pedro Lucas Gomes
