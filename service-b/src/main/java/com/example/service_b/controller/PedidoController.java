package com.example.service_b.controller;

import com.example.service_b.client.ProdutoClient;
import com.example.service_b.modelos.Pedido;
import com.example.service_b.modelos.Produto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final ProdutoClient produtoClient;

    public PedidoController(ProdutoClient produtoClient) {
        this.produtoClient = produtoClient;
    }

    @GetMapping
    public List<Pedido> listarPedidos() {
        List<Produto> produtos = produtoClient.listarProdutos();
        return List.of(new Pedido("Pedido 1", produtos));
    }
}
