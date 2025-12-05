package com.example.service_a.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.service_a.classe.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {}


