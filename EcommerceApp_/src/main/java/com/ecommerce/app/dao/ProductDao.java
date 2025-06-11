package com.ecommerce.app.dao;

import java.math.BigDecimal;
import java.util.List;

import com.ecommerce.app.entity.Product;

public interface ProductDao {
	
	public Integer addProduct(Product request, Long categoryId);
	public List<Product> getProducts();
	public List<Product> getProductsByCategory(Long id);
	public Integer deleteProductById(Long id);

}
