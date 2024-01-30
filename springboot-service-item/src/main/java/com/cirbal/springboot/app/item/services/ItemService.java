package com.cirbal.springboot.app.item.services;

import java.util.List;

import com.cirbal.springboot.app.commons.models.entity.Product;
import com.cirbal.springboot.app.item.models.Item;

public interface ItemService {

	public List<Item> findAll();

	public Product findById(Long id);

	public Item findByIdCantidad(Long id, Integer cantidad);

	public void deleteById(Long id);

	public Product create(Product product);

	public Product updateProduct(Product product, Long id);

}
