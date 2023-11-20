package com.cirbal.springboot.app.products.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cirbal.springboot.app.products.models.Product;
import com.cirbal.springboot.app.products.services.IProductService;

@RestController
public class ProductController {

	@Autowired
	private Environment env;

	@Value("${server.port}")
	private Integer port;
	@Autowired
	private IProductService iProductService;

	@GetMapping("/list")
	public List<Product> listar() {
		return iProductService.findAll().stream().map(producto -> {
			if (producto != null) {
				producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));

//				producto.setPort(port);
			}
			return producto;
		}).collect(Collectors.toList());
	}

	@GetMapping("/ver/{id}")
	public Product detail(@PathVariable Long id) {
		Product producto = iProductService.findById(id);
		if (producto != null) {
//			producto.setPort(port);
			producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));

		}

//		try {
//			Thread.sleep(2000L);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		boolean ok=false;
//		if(!ok) {
//			throw new RuntimeException("No se pudo cargar el producto!");
//		}
		return producto;
	}

	@DeleteMapping("/delete/{id}")
	public void deleteById(@PathVariable Long id) {
		iProductService.deleteById(id);
	}
}
