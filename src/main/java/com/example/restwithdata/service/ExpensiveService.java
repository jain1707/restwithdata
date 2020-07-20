package com.example.restwithdata.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class ExpensiveService {

    @Cacheable("myfirstcache")
    public Integer calculateExpensiveValue(String key){
        return key.hashCode();
    }
}
