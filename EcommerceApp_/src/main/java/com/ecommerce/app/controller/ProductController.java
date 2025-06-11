package com.ecommerce.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.app.apiresponse.CommonResponse;
import com.ecommerce.app.entity.Product;
import com.ecommerce.app.request.ProductRequest;
import com.ecommerce.app.service.ProductService;

@RestController
@RequestMapping("/admin/product")
public class ProductController {
	
	@Autowired
	ProductService productService;

	@PostMapping(value="/add-product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<CommonResponse<String>> addProduct(@ModelAttribute ProductRequest request){
		return ResponseEntity.ok(productService.addProduct(request));
	}
	
	@GetMapping("/get-products")
	public ResponseEntity<CommonResponse<List<Product>>> getProductList(){
		return ResponseEntity.ok(productService.getProducts());
	}
	
	@GetMapping("/get-products/{id}")
	public ResponseEntity<CommonResponse<List<Product>>> getProductByCategory(@PathVariable Long id){
		return ResponseEntity.ok(productService.getProductByCategory(id));
	}
	
	@DeleteMapping("/delete-product")
	public ResponseEntity<CommonResponse<Product>> deleteProductById(@RequestParam Long id){
		return ResponseEntity.ok(productService.deleteProductById(id));
	}
	
}
