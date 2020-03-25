package org.sid.newecomv1.dao;

import org.sid.newecomv1.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@CrossOrigin("*")
@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product,Long> {
    @RestResource(path = "/selectedProducts")
    public List<Product> findBySelectedIsTrue();
    @RestResource(path="/productsByKeyword")
    public List<Product> findByNameContains(@Param("mc") String mc) ;
    @RestResource(path="/promotion")
    public List<Product>findByPromotionIsTrue();
    @RestResource(path="/dispo")
    public List<Product>findByAvailableIsTrue();

}
