# CakeShop 🎂

Um sistema de compras de confeitaria via linha de comando (CLI), desenvolvido em Java como projeto pessoal/acadêmico para praticar arquitetura em camadas, exceções customizadas e persistência em JSON.

> ⚠️ **Status: em desenvolvimento.** As camadas de model e repository estão praticamente prontas; as camadas de service e UI ainda estão em andamento. Veja o [Roadmap](#roadmap) abaixo.

## Visão Geral

O CakeShop simula um fluxo simples de compra em uma confeitaria via terminal:
- Listar produtos disponíveis
- Buscar produtos por tag (chocolate, vegano, sem glúten, etc.)
- Adicionar itens (com quantidade) ao carrinho de compras
- Visualizar o total do carrinho
- Finalizar a compra (checkout simulado), reduzindo o estoque dos produtos

## Tecnologias

- **Java 17**
- **Maven** (gerenciamento de dependências / build)
- **Jackson** (`jackson-databind`) — serialização em JSON, usada como "banco de dados" leve para persistência

## Arquitetura

O projeto segue uma arquitetura em camadas, separando responsabilidades em quatro pacotes:

```
com.deboraayumi
├── model/        → Classes de dados (Product, CartItem, ShoppingCart)
├── repository/    → Leitura/escrita de dados em arquivos JSON (sem regras de negócio)
├── service/       → Lógica de negócio e validações
├── exception/     → Exceções customizadas (extendem RuntimeException)
└── Main.java      → Ponto de entrada da aplicação
```

**Responsabilidade de cada camada:**
- `model` — dados + validação dos próprios atributos (ex: `Product` valida preço, estoque e nome ao ser criado).
- `repository` — sabe buscar/salvar dados (arquivos JSON via Jackson). Nenhuma regra de negócio fica aqui.
- `service` — regras de negócio (ex: montar um `CartItem` a partir de um ID de produto + quantidade, adicionar ao carrinho, salvar). Chama os repositories.
- `exception` — exceções não-checadas customizadas (`InvalidProductIdException`, `InvalidProductArgumentException`), para que os erros de validação e de busca sejam identificados e tratados separadamente.
- UI (planejada) — única camada responsável por ler entradas do usuário e imprimir no console; captura exceções vindas da camada de service e exibe mensagens amigáveis.

## Estrutura do Projeto

```
cakeshop/
├── pom.xml
├── data/                          # Arquivos JSON "banco de dados"
│   └── productDB.json            # Catálogo de produtos (populado via seeder)
└── src/main/java/com/deboraayumi/
    ├── Main.java
    ├── exception/
    │   ├── InvalidProductIdException.java
    │   └── InvalidProductArgumentException.java
    ├── model/
    │   ├── Product.java           # inclui o enum Tag
    │   ├── CartItem.java
    │   └── ShoppingCart.java
    ├── repository/
    │   ├── ProductRepository.java
    │   └── ShoppingCartRepository.java
    └── service/
        ├── ProductService.java
        └── ShoppingCartService.java
```

## Modelo de Dados

**Product**: id, name, price, stock, tags (`List<Tag>`)

**Tag** (enum): `CHOCOLATE, VANNILA, STRAWBERRY, CAKE, COOKIE, PIE, DRINK, BITTER, FRUIT, VEGAN, GLUTEN_FREE, LACTOSE_FREE`

**CartItem**: referência a um `Product` + quantidade selecionada

**ShoppingCart**: lista de `CartItem`, calcula valor total e quantidade total de itens

## Como Executar

### Pré-requisitos
- JDK 17+
- Maven

### Build
```bash
cd cakeshop
mvn compile
```

### Popular o catálogo de produtos (necessário antes de rodar a aplicação)
```bash
mvn exec:java -Dexec.mainClass="com.deboraayumi.utils.ProductSeeds"
```
Isso gera `data/productDB.json` com o catálogo inicial.

### Executar a aplicação
```bash
mvn exec:java -Dexec.mainClass="com.deboraayumi.Main"
```

## Roadmap

- [x] Model `Product` com validação de campos e tags
- [x] Models `CartItem` / `ShoppingCart`
- [x] `ProductRepository` (persistência em JSON via Jackson, path corrigido)
- [x] `ShoppingCartRepository` (leitura/escrita de itens do carrinho)
- [x] Exceções customizadas (`InvalidProductIdException`, `InvalidProductArgumentException`)
- [x] `ShoppingCartService` (adicionar item por ID + quantidade, salvar carrinho)
- [x] Seeder com catálogo inicial de produtos
- [X] Implementar `ProductService` (listagem e busca por tag) — arquivo ainda vazio
- [ ] Construir a interface CLI/menu (listar, buscar, carrinho, checkout)
- [ ] Tela de pagamento simulado
- [ ] Redução de estoque após a compra
- [ ] Conectar `Main.java` ao fluxo da aplicação

## Autora

[Debora Aguiar](https://github.com/deboraayumi) — Estudante de Sistemas de Informação
