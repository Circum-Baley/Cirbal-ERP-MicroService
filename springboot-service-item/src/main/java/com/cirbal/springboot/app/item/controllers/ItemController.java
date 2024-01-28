
package com.cirbal.springboot.app.item.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cirbal.springboot.app.item.models.Item;
import com.cirbal.springboot.app.commons.models.entity.Product;
import com.cirbal.springboot.app.item.services.ItemService;

@RestController
//@RequestMapping("/api/items")
public class ItemController {

	private static final Logger log = LoggerFactory.getLogger(ItemController.class);

	@Autowired
	private CircuitBreakerFactory<?, ?> circutBFactory;
	@Autowired
	@Qualifier("itemServiceFeign")
	private ItemService itemService;

	@GetMapping("/list")
	public List<Item> findAlll() {
		List<Item> item = itemService.findAll();
		return item;
	}

// The code above, is the same up, but this code, is with ResponseEntity
	@GetMapping("/listarr")
	public ResponseEntity<List<Item>> findAll() {
		List<Item> items = itemService.findAll();
		try {
			if (items.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(items, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error while fetching items: {}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

//	@HystrixCommand(fallbackMethod = "fallbackForFindById")
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public ResponseEntity<Item> findById(@PathVariable Long id, @PathVariable Integer cantidad) {
		Item item = itemService.findByIdCantidad(id, cantidad);
		return new ResponseEntity<>(item, HttpStatus.OK);
	}

	// Fallback method for findById
	public ResponseEntity<Item> fallbackForFindById(Long id, Integer cantidad) {
		Item item = new Item();
		return new ResponseEntity<>(item, HttpStatus.OK);
	}

	@GetMapping("/detailProduct/{id}")
	public Product findById(@PathVariable Long id) {
		Product itemByProductId = itemService.findById(id);
		return itemByProductId;
	}

//	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping("/verr/{id}/cantidad/{cantidad}")
	public Item findByIdd(@PathVariable Long id, @PathVariable Integer cantidad) {
//		return itemService.findById(id, cantidad);
		return circutBFactory.create("items").run(() -> itemService.findByIdCantidad(id, cantidad),
				e -> metodoAlternativo(id, cantidad, e));
	}

	public Item metodoAlternativo(Long id, Integer cantidad, Throwable e) {
		log.info(e.getMessage());
		Item item = new Item();
		Product product = new Product();
		item.setCantidad(cantidad);
		product.setId(id);
		product.setName("Producto No Localizado ");
		product.setPrice(000.00);
		item.setProduct(product);
		return item;
	}

	@PostMapping("/createProduct")
	@ResponseStatus(code = HttpStatus.OK)
	public Product create(@RequestBody Product product) {
		Product productCreated = itemService.create(product);
		return productCreated;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		itemService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/updateProduct/{id}")
	public Product updateProduct(@RequestBody Product product, @PathVariable Long id) {
		Product ItemByIdProduct = itemService.updateProduct(product, id);
		return ItemByIdProduct;
	}
}