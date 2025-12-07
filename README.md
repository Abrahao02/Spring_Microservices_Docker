# 🧩 Projeto de Microsserviços — O que eu consegui construir

Este README explica, de forma simples e direta, **o que eu realmente consegui alcançar com o projeto**. Em vez de focar só em teoria, aqui eu descrevo na prática como montei três microsserviços funcionando juntos, rodando em containers Docker e Kubernetes (Minikube) e se comunicando entre si.

---

## 🚀 O que eu consegui fazer

Durante o desenvolvimento deste projeto, consegui montar uma pequena arquitetura de microsserviços, onde cada serviço tem sua própria responsabilidade — exatamente como uma arquitetura de microsserviços deve ser.

O principal objetivo foi **fazer serviços independentes conversarem entre si**, e isso funcionou com sucesso usando **Feign Client**.

Além disso, também aprendi na prática como **containerizar aplicações Spring Boot com Docker** e como fazer elas funcionarem juntas usando `docker-compose` e posteriormente migrar para **Kubernetes** com Minikube.

---

## 🧱 Como ficou a estrutura dos serviços

### 📌 Service A — Produtos

Este serviço é simples e direto: ele devolve uma lista de produtos. É como se fosse o "catálogo" da aplicação.

* **Endpoint:** `GET /produtos`
* **Resposta:** lista de produtos com nome, id e preço

Ele é independente, roda sozinho e expõe apenas o que precisa.

### 📦 Service B — Pedidos

Este serviço depende do Service A. Ele não tem os produtos — ele **pede** essas informações ao Service A.

Ou seja, ele funciona como um cliente do Service A, usando **Feign Client** para consumir o endpoint `/produtos`.

* **Endpoint:** `GET /pedidos`
* Ele monta um pedido pegando um produto lá do Service A

Foi aqui que realmente entendi como funciona a comunicação entre serviços dentro do Docker e Kubernetes.

### 🗄️ Service C — Banco de Dados (PostgreSQL)

Para armazenar os dados de produtos, criei um microsserviço com **PostgreSQL**:

* O Service A lê automaticamente do banco.
* Banco populado com dados de exemplo (Notebook, Mouse).
* Persistência testada com consultas dentro do pod.

---

## 🔗 Comunicação entre serviços

A comunicação acontece assim:

* O **Service B** faz uma requisição **automática** para o **Service A**, sem precisar de URL fixa na máquina local.
* Dentro do Docker ou do Kubernetes, o nome do serviço (`service-a`) vira o "host".

Exemplo da integração:

```java
@FeignClient(name = "produto-service", url = "http://service-a:8080")
public interface ProdutoClient {
    @GetMapping("/produtos")
    List<Produto> listarProdutos();
}
```

Assim, um serviço conversa com o outro como se fossem partes separadas de um sistema maior.

---

## 🐳 O que consegui aprender com Docker

Aprendi a:

* Criar as imagens dos serviços usando `Dockerfile`
* Subir tudo junto com `docker-compose`
* Fazer os serviços se enxergarem dentro da rede interna do Docker

O `docker-compose.yml` garante que os serviços subam na ordem correta e que um consiga acessar o outro.

### Comando para subir os containers:

```bash
docker-compose up --build
```

---

## ☸️ Migração para Kubernetes (Minikube)

Depois de validar com Docker, migrei os microsserviços para Kubernetes usando **Minikube**.

O que foi implantado no cluster:

* Deployment do **PostgreSQL**
* Deployment do **Service A**
* Deployment do **Service B**
* Services (ClusterIP) para comunicação interna
* Teste de comunicação entre Service A e Service B usando Feign
* Teste de conexão com o banco PostgreSQL

### Comandos utilizados:

* Ver serviços rodando:

```bash
kubectl get svc
```

* Ver pods:

```bash
kubectl get pods
```

* Acessar o banco dentro do pod:

```bash
kubectl exec -it postgres-xxxxx -- psql -U postgres -d produtosdb
```

* Verificar logs dos serviços:

```bash
kubectl logs deployment/service-a
kubectl logs deployment/service-b
```

---

## 🔍 Testando os Endpoints

### ✔ Service A

```
GET http://localhost:8080/produtos
```

**Retorno esperado:**

```json
[
  { "id": 1, "nome": "Notebook", "preco": 3500.0 },
  { "id": 2, "nome": "Mouse", "preco": 120.0 }
]
```

### ✔ Service B

```
GET http://localhost:8081/pedidos
```

**Retorno esperado:**

```json
{
  "numeroPedido": 1,
  "produto": {
    "id": 1,
    "nome": "Notebook",
    "preco": 3500.0
  }
}
```

---

## 📚 O que foi alcançado neste projeto

* Criei **três microsserviços independentes** (Produtos, Pedidos e PostgreSQL).
* Configurei **comunicação entre serviços usando Feign Client**.
* Coloquei cada microsserviço em um **container Docker**.
* Migrei toda a solução para o **Kubernetes utilizando Minikube**.
* Validei comunicação interna entre pods e serviços.
* Configurei o Service A para ler do banco PostgreSQL automaticamente.
* Verifiquei logs, pods e serviços dentro do cluster.

---

## 🎯 Conclusão

Este projeto permitiu entender, na prática:

* Como separar responsabilidades entre serviços
* Como fazer eles se comunicarem usando Feign Client
* Como rodar tudo usando Docker e Docker Compose
* Como migrar para Kubernetes (Minikube)
* Como organizar uma mini arquitetura de microsserviços funcional

O foco não foi criar algo complexo, mas **fazer microsserviços simples funcionarem de verdade**, comunicando-se corretamente entre si.

---

## 👨‍💻 Autor

**Eduardo de Sá Abrahão**
Estudante de Engenharia de Software

📌 LinkedIn: [https://www.linkedin.com/in/eduardo-abrah%C3%A3o-dev/](https://www.linkedin.com/in/eduardo-abrah%C3%A3o-dev/)")
