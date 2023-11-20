package com.cirbal.springboot.app.products.services;

import java.util.List;

import com.cirbal.springboot.app.products.models.Product;

public interface IProductService {

	public List<Product> findAll();

	public Product findById(Long id);

	public Product findByName(String name);

	public void deleteById(Long id);

	public List<Product> findAllPrice();

}
