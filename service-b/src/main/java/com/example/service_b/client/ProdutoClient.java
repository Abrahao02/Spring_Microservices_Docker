package com.example.service_b.client;

import com.example.service_b.modelos.Produto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "produto-service", url = "http://service-a:8080")
public interface ProdutoClient {
    @GetMapping("/produtos")
    List<Produto> listarProdutos();
}
