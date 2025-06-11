package com.ecommerce.app.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.app.entity.Category;

public interface CategoryDao {
	
	public Integer createCategory(String name, MultipartFile imageFile);
	public List<Category> getListOfCategory();
	public Integer deleteCategory(Long id);
}
