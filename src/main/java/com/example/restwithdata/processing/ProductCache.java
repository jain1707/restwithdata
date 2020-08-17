package com.example.restwithdata.processing;

import com.example.restwithdata.model.Product;
import com.example.restwithdata.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
public class ProductCache {

    @Autowired
    ProductRepository repo;

    private RedisTemplate<String,Object> redisTemplate;
    private HashOperations<String,Long,Product> hashOperations;
    private static String REDIS_KEY = "PRODUCT";

    @Autowired
    public ProductCache(RedisTemplate<String,Object> redisTemplate){
        this.redisTemplate=redisTemplate;
    }

    @PostConstruct
    private void init(){
        redisTemplate.expire("PRODUCT",10, TimeUnit.MINUTES);
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Cacheable(value = "myfirstcache", key = "#id", unless = "#result==null", cacheManager = "cacheManager1Hour")
    public Product findById(Long id) {
        Optional<Product> o= repo.findById(id);
        if(o.isPresent()) return o.get();
        return null;
    }

    @Cacheable(value = "cacheV1", unless = "#result==null")
    public Product findById1(Long id) {
        Optional<Product> o= repo.findById(id);
        if(o.isPresent()) return o.get();
        return null;
    }

    public Product findById2(Long id) {
        Product p = hashOperations.get(REDIS_KEY,id);
        if(p!=null) return p;
        Optional<Product> o= repo.findById(id);
        if(o.isPresent()) {
            hashOperations.put(REDIS_KEY,id,o.get());
            return o.get();
        }
        return null;
    }

    @Cacheable(value="myfirstcache",key="#root.methodName")
    public List<Product> findAll() {
      //  return repo.findAll();
        return new ArrayList<Product>(repo.findAll());
    }

    @Cacheable(value="myfirstcache")
    public List<Product> findAll1() {
        //  return repo.findAll();
        return new ArrayList<Product>(repo.findAll());
    }

 }
