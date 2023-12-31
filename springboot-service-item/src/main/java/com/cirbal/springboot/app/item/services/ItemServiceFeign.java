package com.cirbal.springboot.app.item.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.cirbal.springboot.app.item.clientes.ProductoClienteRest;
import com.cirbal.springboot.app.item.models.Item;

@Service("itemServiceFeign")
public class ItemServiceFeign implements ItemService {

	@Autowired
	private ProductoClienteRest productoClienteRest;

	@Override
	public List<Item> findAll() {
		return productoClienteRest.listar().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		return new Item(productoClienteRest.detaildById(id), cantidad);
	}

	@Override
	public void deleteById(Long id) {
		productoClienteRest.deleteById(id);
	}

}
