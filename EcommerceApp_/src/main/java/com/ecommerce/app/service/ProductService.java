package com.ecommerce.app.service;

import java.util.List;

import com.ecommerce.app.apiresponse.CommonResponse;
import com.ecommerce.app.entity.Product;
import com.ecommerce.app.request.ProductRequest;

public interface ProductService {
	
	public CommonResponse<String> addProduct(ProductRequest request);
	public CommonResponse<List<Product>> getProducts();
	public CommonResponse<List<Product>> getProductByCategory(Long id);
	public CommonResponse<Product> deleteProductById(Long id);
}
