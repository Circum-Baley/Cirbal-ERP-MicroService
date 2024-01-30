package com.cirbal.springboot.app.item.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cirbal.springboot.app.commons.models.entity.Product;
import com.cirbal.springboot.app.item.clientes.ProductoClienteRest;
import com.cirbal.springboot.app.item.models.Item;

@Service("itemServiceImpl")
public class ItemServiceImpl implements ItemService {

	@Autowired
	private RestTemplate clientRestTemplate;

	@Override
	public Item findByIdCantidad(Long id, Integer cantidad) {
		Map<String, String> pathVariableMap = new HashMap<String, String>();
		pathVariableMap.put("id", id.toString());
		Product product = clientRestTemplate.getForObject("http://service-products/ver/{id}", Product.class,
				pathVariableMap);
		return new Item(product, cantidad);
	}

	@Override
	public void deleteById(Long id) {
		Map<String, String> pathVariable = new HashMap<String, String>();
		pathVariable.put("id", id.toString());
		clientRestTemplate.delete("http://service-product/delete/{id}", pathVariable);
	}

	@Override
	public List<Item> findAll() {
		List<Product> products = Arrays
				.asList(clientRestTemplate.getForObject("http://service-products/list", Product[].class));
		return products.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Product updateProduct(Product product, Long id) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());

		HttpEntity<Product> bodyProduct = new HttpEntity<Product>(product);
		ResponseEntity<Product> responseProduct = clientRestTemplate.exchange("http://service-product/update/{id}",
				HttpMethod.PUT, bodyProduct, Product.class);
		return responseProduct.getBody();
	}

	@Override
	public Product create(Product product) {
		HttpEntity<Product> bodyProduct = new HttpEntity<Product>(product);
		ResponseEntity<Product> responseProduct = clientRestTemplate.exchange("http://service-product/create",
				HttpMethod.POST, bodyProduct, Product.class);
		Product productResponse = responseProduct.getBody();
		return productResponse;
	}

	@Override
	public Product findById(Long id) {
	    // Configurar la URL y la entidad de solicitud HTTP
	    String url = "http://service-product/detail/{id}";
	    HttpEntity<Product> requestEntity = new HttpEntity<>(null); // Puedes incluir el cuerpo o encabezados de solicitud según sea necesario
	    // Hacer la solicitud y obtener la respuesta
	    ResponseEntity<Product> responseEntity = clientRestTemplate.exchange(url, HttpMethod.GET, requestEntity, Product.class, id);
	    // Verificar el código de estado de la respuesta
	    if (responseEntity.getStatusCode() == HttpStatus.OK) {
	        // Si la respuesta es exitosa, obtener el cuerpo de la respuesta (Product)
	        Product product = responseEntity.getBody();
	        return product;
	    } else {
	        // Manejar el caso de respuesta no exitosa, puedes lanzar una excepción o devolver null según tus necesidades.
	        return null;
	    }
	}

//
//	@Override
//	public Item findByIdCantidad(Long id, Integer cantidad) {
//		Map<String, String> pathVariableMap = new HashMap<String, String>();
//		pathVariableMap.put("id", id.toString());
//		Product product = clientRestTemplate.getForObject("http://service-products/ver/{id}", Product.class,
//				pathVariableMap);
//		return new Item(product, cantidad);
//	}
}
