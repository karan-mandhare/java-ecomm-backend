package com.ecommerce.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.app.apiresponse.CommonResponse;
import com.ecommerce.app.entity.Category;
import com.ecommerce.app.service.CategoryService;

@RestController
@RequestMapping("/admin/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;

	@PostMapping("/add-category")
	public ResponseEntity<CommonResponse<String>> createCategory(@RequestParam("name") String name, 
			@RequestParam("image") MultipartFile imageFile){
		return ResponseEntity.ok(categoryService.addCategory(name, imageFile));
	}
	
	@GetMapping("/get-categories")
	public ResponseEntity<CommonResponse<List<Category>>> getListOfCategory(){
		return ResponseEntity.ok(categoryService.getCategories());
	}
	
	@DeleteMapping("/delete-category/{id}")
	public ResponseEntity<CommonResponse<String>> deleteCategory(@PathVariable Long id){
		return ResponseEntity.ok(categoryService.deleteCategory(id));
	}
}
