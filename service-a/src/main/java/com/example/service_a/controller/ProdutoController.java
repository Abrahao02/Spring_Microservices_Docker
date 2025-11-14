package com.example.service_a.controller;

import com.example.service_a.classe.Produto;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id) {
        return new Produto(id, "Produto " + id, 99.9);
    }

    @PostMapping
    public Produto criar(@RequestBody Produto produto) {
        return produto;
    }

    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        return new Produto(
                id,
                produto.nome(),
                produto.preco()
        );
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id) {
        return "Produto " + id + " deletado com sucesso.";
    }
}
