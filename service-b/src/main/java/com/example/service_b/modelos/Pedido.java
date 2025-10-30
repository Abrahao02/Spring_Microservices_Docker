package com.example.service_b.modelos;

import java.util.List;

public record Pedido(String descricao, List<Produto> produtos) {}
