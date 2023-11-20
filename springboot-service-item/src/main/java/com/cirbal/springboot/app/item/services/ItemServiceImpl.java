package com.cirbal.springboot.app.item.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cirbal.springboot.app.item.models.Item;
import com.cirbal.springboot.app.item.models.Product;

@Service("itemServiceImpl")
public class ItemServiceImpl implements ItemService {

	@Autowired
	private RestTemplate clientRestTemplate;

	@Override
	public List<Item> findAll() {
		List<Product> products = Arrays
				.asList(clientRestTemplate.getForObject("http://service-products/list", Product[].class));
		return products.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		Map<String, String> pathVariableMap = new HashMap<String, String>();
		pathVariableMap.put("id", id.toString());
		Product product = clientRestTemplate.getForObject("http://service-products/ver/{id}", Product.class,
				pathVariableMap);
		return new Item(product, cantidad);
	}

	@Override
	public void deleteById(Long id) {
		String deleteUrl = "http://localhost:8001/delete/{id}";
		clientRestTemplate.delete(deleteUrl, id);
	}
//	public void deleteById(Long id) {
//	String deleteUrl = "http://localhost:8001/delete/{id}";
//	URI uri = UriComponentsBuilder.fromUriString(deleteUrl).buildAndExpand(id).toUri();
//	clientRestTemplate.delete(uri);
//}

}
