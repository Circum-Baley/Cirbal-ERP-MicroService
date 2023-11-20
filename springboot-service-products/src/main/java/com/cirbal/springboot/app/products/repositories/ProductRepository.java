package com.cirbal.springboot.app.products.repositories;

import org.springframework.data.repository.CrudRepository;

import com.cirbal.springboot.app.products.models.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

}
