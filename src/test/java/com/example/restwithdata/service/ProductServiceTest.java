package com.example.restwithdata.service;

import com.example.restwithdata.entity.Product;
import com.example.restwithdata.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@DataJpaTest
class ProductServiceTest {
    ProductService ps= new ProductService();
    @Mock
    ProductRepository pr;
    @BeforeEach
    void setUp() {
        ps.setRepo(pr);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAll() {
        List<Product> plist = new ArrayList<>();
        plist.add(new Product("TShirt","Reeeobk","dwad","dwad"));
        when(pr.findAll()).thenReturn(plist);
        Response r = ps.getAll();
        List<Product> nplist = (List<Product>) r.getEntity();
        System.out.println(plist);
        assertEquals(r.getStatus(),200);
        assertEquals(nplist.size(),1);

    }

    @Test
    void getById() {
    }

    @Test
    void create() {
    }

    @Test
    void getByCategory() {
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void updateProduct() {
    }
}