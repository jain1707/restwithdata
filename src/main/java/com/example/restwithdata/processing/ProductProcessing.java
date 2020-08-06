package com.example.restwithdata.processing;

import com.example.restwithdata.model.Product;
import com.example.restwithdata.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductProcessing {

    @Autowired
    ProductRepository repo;

    @Cacheable(value = "myfirstcache", key = "#id")
    public Optional<Product> findById(Long id) {
            return repo.findById(id);
    }
}
