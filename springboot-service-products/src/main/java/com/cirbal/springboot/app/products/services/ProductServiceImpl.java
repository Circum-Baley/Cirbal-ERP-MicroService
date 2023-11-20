package com.cirbal.springboot.app.products.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cirbal.springboot.app.products.models.Product;
import com.cirbal.springboot.app.products.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Product findById(Long id) {

		return productRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteById(Long id) {
		productRepository.deleteById(id);
		
	}
	@Override
	public Product findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findAllPrice() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
