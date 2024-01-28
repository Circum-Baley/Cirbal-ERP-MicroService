package com.cirbal.springboot.app.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cirbal.springboot.app.commons.models.entity.Product;


@FeignClient(name = "service-products")
public interface ProductoClienteRest {

	@GetMapping("/list")
	public List<Product> listar();

	@GetMapping("/detail/{id}")
	public Product detaildById(@PathVariable Long id);

	@DeleteMapping("/delete/{id}")
	public void deleteById(@PathVariable Long id);

	@PostMapping("/createProduct")
	public Product create(@RequestBody Product product);

	@PutMapping("/update/{id}")
	public Product updateProduct(@RequestBody Product product, @PathVariable Long id);
}
