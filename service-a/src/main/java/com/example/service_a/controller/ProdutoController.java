package com.example.service_a.controller;

import com.example.service_a.classe.Produto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @GetMapping
    public List<Produto> listarProdutos() {
        return List.of(
                new Produto(1L, "Notebook", 3500.0),
                new Produto(2L, "Mouse", 120.0)
        );
    }
}
