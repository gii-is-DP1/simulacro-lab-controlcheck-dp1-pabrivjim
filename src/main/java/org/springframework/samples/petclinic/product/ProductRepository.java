package org.springframework.samples.petclinic.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;



public interface ProductRepository extends  CrudRepository<Product, String>{
    

    @Query("SELECT P FROM ProductType P")
    List<ProductType> findAllProductTypes();
    Optional<Product> findById(int id);
    Product findByName(String name);
    Product save(Product p);

    @Query("SELECT P FROM ProductType P where P.name LIKE :name%")
    ProductType findProductTypeByName(String name);

    List<Product> findAll();
}
