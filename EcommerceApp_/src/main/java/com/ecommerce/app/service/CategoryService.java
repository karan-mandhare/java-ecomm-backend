package com.ecommerce.app.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.app.apiresponse.CommonResponse;
import com.ecommerce.app.entity.Category;

public interface CategoryService {

	public CommonResponse<String> addCategory(String name, MultipartFile imageFile);

	public CommonResponse<List<Category>> getCategories();

	public CommonResponse<String> deleteCategory(Long id);
}
