package com.example.restwithdata.service;

import com.example.restwithdata.model.Product;
import com.example.restwithdata.processing.ProductCache;
import com.example.restwithdata.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("product")
@Component
public class ProductService implements Service<Response,Product,Long> {

    @Autowired
    ProductRepository repo;

    @Autowired
    ProductCache pros;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        Iterable<Product> list = pros.findAll();
        return Response.status(200).entity(list).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") Long id){
        Product p = pros.findById(id);
        if(p!=null){
            return Response.status(200).entity(p).build();
        }
        return Response.status(404).build();
    }

    @GET
    @Path("n")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll1(){
        Iterable<Product> list = pros.findAll1();
        return Response.status(200).entity(list).build();
    }

    @GET
    @Path("v1/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get1(@PathParam("id") Long id){
        Product p = pros.findById1(id);
        if(p!=null){
            return Response.status(200).entity(p).build();
        }
        return Response.status(404).build();
    }

    @GET
    @Path("v2/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get2(@PathParam("id") Long id){
        Product p = pros.findById2(id);
        if(p!=null){
            return Response.status(200).entity(p).build();
        }
        return Response.status(404).build();
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
    public Response delete(@PathParam("Id") Long id){
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
    public Response update(Product p){
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
