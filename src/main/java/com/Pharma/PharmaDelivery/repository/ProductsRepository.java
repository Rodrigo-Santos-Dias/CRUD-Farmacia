package com.Pharma.PharmaDelivery.repository;


import com.Pharma.PharmaDelivery.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products, Long>{

    public List <Products> findAllByNameContainingIgnoreCase(@Param("name")String name);

}