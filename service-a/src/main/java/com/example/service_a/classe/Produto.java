package com.example.service_a.classe;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("produtos")
public class Produto {

    @Id
    private Long id;

    private String nome;
    private Double preco;

    public Produto() {}

    public Produto(Long id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public Double getPreco() { return preco; }
}

