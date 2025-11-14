FROM ubuntu:22.04

RUN apt update && \
    apt install -y mysql-server && \
    rm -rf /var/lib/apt/lists/*

EXPOSE 3306

CMD ["mysqld", "--bind-address=0.0.0.0"]
