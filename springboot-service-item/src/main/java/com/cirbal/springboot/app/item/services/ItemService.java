package com.cirbal.springboot.app.item.services;

import java.util.List;

import com.cirbal.springboot.app.item.models.Item;

public interface ItemService {

	public List<Item> findAll();
	public Item findById(Long id,Integer cantidad);
	public void deleteById(Long id);	
}
