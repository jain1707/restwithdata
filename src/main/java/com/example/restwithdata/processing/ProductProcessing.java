package com.example.restwithdata.processing;

import com.example.restwithdata.model.Product;
import com.example.restwithdata.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class ProductProcessing {

    @Autowired
    ProductRepository repo;

    @Cacheable(value = "myfirstcache", key = "#id")
    public Product findById(Long id) {
        Optional<Product> o= repo.findById(id);
        if(o.isPresent()) return o.get();
        return null;
    }

    @Cacheable
    public List<Product> findAll() {
      //  return repo.findAll();
        return new ArrayList<Product>(repo.findAll());
    }
}
