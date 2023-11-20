package com.cirbal.springboot.app.item.models;

public class Item {

	private Product product;
	private Integer cantidad;

	public Item(Product product, Integer cantidad) {
		this.product = product;
		this.cantidad = cantidad;
	}

	public Item() {
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getTotal() {
		if (product != null) {
			return product.getPrice() * cantidad.doubleValue();
		}
		return Double.valueOf(000.00);
	}

	@Override
	public String toString() {
		return "Item [product=" + product + "]";
	}

}
