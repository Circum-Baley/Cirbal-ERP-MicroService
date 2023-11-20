package com.cirbal.springboot.app.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cirbal.springboot.app.item.models.Product;

@FeignClient(name = "service-products")
public interface ProductoClienteRest {
	
	
	@GetMapping("/list")
	public List<Product> listar();

	@GetMapping("/ver/{id}")
	public Product detaildById(@PathVariable Long id);
	
	@DeleteMapping("/delete/{id}")
	public void deleteById(@PathVariable Long id);

}
