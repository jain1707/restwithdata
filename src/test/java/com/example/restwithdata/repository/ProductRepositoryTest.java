package com.example.restwithdata.repository;

import com.example.restwithdata.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {
    @Autowired
    ProductRepository pr;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        pr.deleteAll();
    }

    @Test
    void findByCategory() {
        Product p = new Product("TShirt","Reebok","XL","Cloth");
        pr.save(p);
        List<Product> fp = pr.findByCategory("Cloth");
        assertNotNull(fp);
        assertEquals(fp.size(),1);
        assertEquals(fp.get(0).getName(),p.getName());

    }

    @Test
    void createProduct(){
        Product p = new Product("TShirt","Reebok","XL","Cloth");
        Product np = pr.save(p);
        assertNotNull(np);
        assertEquals(p.getName(),np.getName());
    }

    @Test
    void getProduct(){
        Product p = new Product("TShirt","Reebok","XL","Cloth");
        pr.save(p);
        List<Product> np = pr.findAll();
        assertNotNull(np);
        assertEquals(np.size(),1);
        assertEquals(np.get(0).getName(),p.getName());
    }

}