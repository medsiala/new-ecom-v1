package org.sid.newecomv1.web;

import org.sid.newecomv1.dao.ProductRepository;
import org.sid.newecomv1.entities.Product;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.nio.file.Files;
import java.nio.file.Paths;
@CrossOrigin("*")
@RestController
    public class catalogueRestController {
    private ProductRepository productRepository;

    public catalogueRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @GetMapping(path="/photoProduct/{id}",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
        Product p=productRepository.findById(id).get();
        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/eCom/product/"+p.getPhoto()));

    }
    @PostMapping(path = "/uploadPhoto/{id}")
    public void uploadPhoto(MultipartFile file,@PathVariable Long id) throws Exception{
        Product p =productRepository.findById(id).get();
        p.setPhoto(id+".png");
        Files.write(Paths.get(System.getProperty("user.home")+"/ecom/product/"+p.getPhoto()),file.getBytes());
        productRepository.save(p) ;

    }
}
