package com.cirbal.springboot.app.item.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Env;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cirbal.springboot.app.item.models.Item;
import com.cirbal.springboot.app.item.models.Product;
import com.cirbal.springboot.app.item.services.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//@EnableHystrix
@RestController
public class ItemController {

	@Autowired
	@Qualifier("itemServiceImpl")
	private ItemService itemService;

	@GetMapping("/list")
	public List<Item> findAll(@RequestParam) {
		return itemService.findAll();
	}

	
	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item findById(@PathVariable Long id, @PathVariable Integer cantidad) {
		return itemService.findById(id, cantidad);
	}

	public Item metodoAlternativo(Long id, Integer cantidad) {
		Item item = new Item();
		Product product = new Product();
		item.setCantidad(cantidad);
		product.setId(id);
		product.setName(" Producto Por Defecto Pero En Si Se Puede Programar BCE");
		product.setPrice(000.0);
		product.setCreatedAt(null);
		product.setPort(1111);
		item.setProduct(product);
		return item;
	}

	@DeleteMapping("/delete/{id}")
	public void deleteById(@PathVariable Long id) {
		itemService.deleteById(id);
	}

}
