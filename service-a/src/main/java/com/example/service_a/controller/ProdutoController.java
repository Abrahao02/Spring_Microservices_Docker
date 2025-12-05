package com.example.service_a.controller;

import com.example.service_a.classe.Produto;
import com.example.service_a.repository.ProdutoRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Produto> listarProdutos() {
        return (List<Produto>) repository.findAll();
    }
}
