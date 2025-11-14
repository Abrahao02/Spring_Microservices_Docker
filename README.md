
# 🧩 Microsserviços com Spring Boot, Docker, Kubernetes e Feign Client

Este projeto demonstra a criação e integração de dois microsserviços desenvolvidos em **Spring Boot**, utilizando **Docker**, **Docker Compose**, **Minikube (Kubernetes)** e comunicação via **Feign Client**.  
Também inclui a criação de uma imagem **MySQL customizada**, publicação no Docker Hub e implantação completa em Kubernetes.

---

## 🚀 Tecnologias Utilizadas
- Java 17  
- Spring Boot  
- Spring Cloud OpenFeign  
- Docker  
- Docker Compose  
- Kubernetes / Minikube  
- Maven  

---

# 📘 Parte 1 — Máquinas Virtuais vs Containers

## 🔍 Diferenças entre máquina virtual e container
| Máquina Virtual (VM) | Container Docker |
|----------------------|------------------|
| Executa um sistema operacional completo | Compartilha o kernel do host |
| Pesada, consume mais RAM/CPU | Leve e rápida |
| Inicialização lenta | Inicia em segundos |
| Ideal para isolamento total | Ideal para microsserviços |

## 🎯 Benefícios do Docker
- Portabilidade  
- Baixo consumo de recursos  
- Rapidez no deploy  
- Reprodutibilidade  
- Escalabilidade em microsserviços  

---

# 📘 Parte 2 — Contêineres e Arquitetura de Microsserviços

## 🐳 Arquitetura do Docker
- **Docker Engine:** responsável por criar e executar containers  
- **Docker Images:** blueprint imutável  
- **Docker Containers:** instâncias em execução  
- **Docker Hub / Registry:** repositório de imagens  
- **Docker Compose:** orquestra múltiplos containers  

## 🏗️ O que é Dockerfile?
Arquivo que descreve como construir a imagem de um serviço.

### Exemplo usado no projeto:
```Dockerfile
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY target/service-a-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

## 🧰 Comandos úteis
```bash
docker build -t service-a .
docker run -p 8080:8080 service-a
docker images
docker ps
```

## 🔀 Docker Compose
Orquestra vários containers rodando juntos.

### Exemplo:
```yaml
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

---

# 📘 Parte 3 — Migração para Kubernetes (Minikube)

## 🛢️ MySQL customizado criado para o projeto
```Dockerfile
FROM ubuntu:22.04
RUN apt update && apt install -y mysql-server && rm -rf /var/lib/apt/lists/*
EXPOSE 3306
CMD ["mysqld", "--bind-address=0.0.0.0"]
```

Imagem criada:
```bash
docker build -t mysql-custom .
docker tag mysql-custom eduardo/mysql-custom:1.0
docker push eduardo/mysql-custom:1.0
```

## 📦 YAMLs de implantação no Kubernetes

### ✔️ MySQL
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: mysql
spec:
  replicas: 1
  selector:
    matchLabels:
      app: mysql
  template:
    metadata:
      labels:
        app: mysql
    spec:
      containers:
        - name: mysql
          image: mysql-custom:latest
          ports:
            - containerPort: 3306
```

### ✔️ Service A
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: service-a
spec:
  replicas: 1
  selector:
    matchLabels:
      app: service-a
  template:
    metadata:
      labels:
        app: service-a
    spec:
      containers:
        - name: service-a
          image: service-a:latest
          ports:
            - containerPort: 8080
```

### ✔️ Service B
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: service-b
spec:
  replicas: 1
  selector:
    matchLabels:
      app: service-b
  template:
    metadata:
      labels:
        app: service-b
    spec:
      containers:
        - name: service-b
          image: service-b:latest
          ports:
            - containerPort: 8081
          env:
            - name: SERVICE_A_URL
              value: "http://service-a:8080"
```

Aplicando no Kubernetes:
```bash
kubectl apply -f k8s/
kubectl get pods
```

---

# 📘 Parte 4 — Microsserviços Spring Boot

## 🧱 Service A — Produtos
Endpoints criados:
- `GET /produtos`
- `GET /produtos/{id}`

## 📦 Service B — Pedidos
- Consome Service A via Feign
- Endpoints:
  - `GET /pedidos`
  - `GET /saudacao`

Exemplo Feign:
```java
@FeignClient(name = "produto-service", url = "${SERVICE_A_URL}")
public interface ProdutoClient {
    @GetMapping("/produtos")
    List<Produto> listarProdutos();
}
```

---

# 🧪 Testes de Funcionamento

### ✔️ Service A:
```
GET http://localhost:8080/produtos
```

### ✔️ Service B:
```
GET http://localhost:8081/pedidos
```

---

# ✍️ Autor
**Eduardo de Sá Abrahão**  
Estudante de Engenharia de Software  
📎 LinkedIn: https://www.linkedin.com/in/eduardo-abrah%C3%A3o-dev/

---

# 📄 Observação Final
Este README serve como documentação completa do projeto, cobrindo:
- Docker
- Compose
- Microsserviços
- Kubernetes
- MySQL customizado
- Comunicação via Feign Client
