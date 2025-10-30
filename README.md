# 🧩 Microsserviços com Spring Boot, Docker e Feign Client

Este projeto demonstra a criação e integração de dois microsserviços desenvolvidos em **Spring Boot**, utilizando **Docker** e comunicação via **Feign Client**.

## 🚀 Tecnologias Utilizadas
- Java 17
- Spring Boot
- Spring Cloud OpenFeign
- Docker
- Docker Compose
- Maven

---

## ⚙️ Estrutura dos Microsserviços

### 🧱 Service A — Produto
- Responsável por expor informações de produtos.
- Endpoint principal:  
  `GET /produtos`

### 📦 Service B — Pedido
- Consome o Service A via **Feign Client** para montar pedidos com informações de produtos.
- Endpoint principal:  
  `GET /pedidos`

---

## 🔗 Comunicação entre os Serviços
O **Service B** consome os dados do **Service A** através do Feign Client configurado da seguinte forma:

```java
@FeignClient(name = "produto-service", url = "http://service-a:8080")
public interface ProdutoClient {
    @GetMapping("/produtos")
    List<Produto> listarProdutos();
}
```

- Dentro do ambiente Docker, o `service-b` acessa `service-a` pela URL interna `http://service-a:8080`.
- Fora do container (via navegador ou Postman), o acesso é feito por:
    - **Service A:** [http://localhost:8080/produtos](http://localhost:8080/produtos)
    - **Service B:** [http://localhost:8081/pedidos](http://localhost:8081/pedidos)

---

## 🐳 Docker

Cada serviço possui um `Dockerfile` com as instruções para construir sua imagem.  
O arquivo `docker-compose.yml` é responsável por orquestrar os dois containers.

### Exemplo de `docker-compose.yml`
```yaml
version: '3'
services:
  service-a:
    build: ./service-a
    ports:
      - "8080:8080"

  service-b:
    build: ./service-b
    ports:
      - "8081:8081"
    depends_on:
      - service-a
```

### Executando os containers
```bash
docker-compose up --build
```

---

## 🧪 Testando os endpoints

1. **Service A**
   ```bash
   GET http://localhost:8080/produtos
   ```
   **Resposta:**
   ```json
   [
     {
       "nome": "Notebook Dell",
       "preco": 4500.0
     }
   ]
   ```

2. **Service B**
   ```bash
   GET http://localhost:8081/pedidos
   ```
   **Resposta:**
   ```json
   {
     "numeroPedido": 1,
     "produto": {
       "nome": "Notebook Dell",
       "preco": 4500.0
     }
   }
   ```

---

## 🧑‍💻 Autor
**Eduardo de Sá Abrahão**  
Estudante de Engenharia de Software — Desenvolvendo soluções com Java e Spring Boot.

📧 [LinkedIn](https://www.linkedin.com/in/eduardo-abrah%C3%A3o-dev/)

---

## 📚 Descrição Resumida para Relatório
Foram criados dois microsserviços:
- **Service-A (Produto)**: expõe um endpoint com informações de produtos.
- **Service-B (Pedido)**: consome os dados do Service-A via **FeignClient**.  
  Ambos foram configurados com **Docker**, com um arquivo `docker-compose.yml` que permite a execução simultânea e comunicação entre os serviços.
