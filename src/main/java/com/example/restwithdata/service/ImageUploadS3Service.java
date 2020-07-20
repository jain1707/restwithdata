package com.example.restwithdata.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import javafx.scene.media.Media;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;

@Path("image")
@Service
public class ImageUploadS3Service {

    @Autowired
    AmazonS3 s3;

    @Autowired
    ExpensiveService es;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

/*    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/upload")
    public Response uploadProductImage(@FormDataParam("file") InputStream in,
                                       @FormDataParam("file") FormDataContentDisposition fdcd){
        if(fdcd!=null && fdcd.getFileName()!=null){
            ObjectMetadata om = new ObjectMetadata();
            om.setContentType(fdcd.getType());
            TransferManager tm = TransferManagerBuilder.defaultTransferManager();
            tm.upload(bucket,fdcd.getFileName(),in,om);

            URL url = s3.getUrl(bucket,fdcd.getFileName());
            return Response.status(200).entity(url).build();
        }
        return  Response.status(300).entity("File data not found").build();
    }*/

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response imageServiceStatus(String in){
        return Response.status(200).entity("Yah!! Upload service is running"+es.calculateExpensiveValue(in)).build();
    }


    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createFile(String input){
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ObjectMetadata om = new ObjectMetadata();
        om.setContentType(MediaType.MULTIPART_FORM_DATA);
        TransferManager tm = TransferManagerBuilder.defaultTransferManager();
        tm.upload(bucket,"File",in,om);
        URL url = s3.getUrl(bucket,"File");
        return Response.status(200).entity(url.toString()).build();
    }
}
