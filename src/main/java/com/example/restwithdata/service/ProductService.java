package com.example.restwithdata.service;

import com.amazonaws.services.s3.AmazonS3;
import com.example.restwithdata.entity.Product;
import com.example.restwithdata.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("product/")
public class ProductService {

    @Autowired
    ProductRepository repo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        Iterable<Product> list = repo.findAll();
        return Response.status(200).entity(list).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") long id){
        Optional<Product> p = repo.findById(id);
        if(p.isPresent()){
            return Response.status(200).entity(p).build();
        }
        return Response.status(200).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Product p){
        Product product = repo.save(p);
        return Response.status(200).entity(product).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("cat/{cat}")
    public Response getByCategory(@PathParam("cat") String cat){
        List<Product> ll = repo.findByCategory(cat);
        return Response.status(200).entity(ll).build();
    }

    @DELETE
    @Path("delete/{Id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("Id") long id){
          Optional<Product> o = repo.findById(id);
          if(o.isPresent()){
              repo.delete(o.get());
              return Response.status(200).entity(o.get()).build();
          }
        return Response.status(202).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(Product p){
        System.out.println(p);
        Optional<Product> pr = repo.findById(p.getId());
        if(pr.isPresent()) {
          Product newp = pr.get();
          newp.setName(p.getName());
            repo.save(p);
            return Response.status(201).entity(newp).build();
       }else{
            repo.save(p);
            return Response.status(201).entity(p).build();
        }

    }

    public void setRepo(ProductRepository repo) {
        this.repo = repo;
    }
}
