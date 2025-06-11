package com.ecommerce.app.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.app.apiresponse.CommonResponse;
import com.ecommerce.app.dao.CategoryDao;
import com.ecommerce.app.entity.Category;
import com.ecommerce.app.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryDao categoryDao;

	@Override
	public CommonResponse<String> addCategory(String name, MultipartFile imageFile) {
		CommonResponse<String> response = new CommonResponse<>();
		int res = categoryDao.createCategory(name, imageFile);
		if(res > 0) {
			response.setSuccess(true);
			response.setMessage("Category saved successfully");
			return response;
		}
		response.setSuccess(false);
		response.setMessage("Error occur while creating category");
		return response;
	}

	@Override
	public CommonResponse<List<Category>> getCategories() {
		CommonResponse<List<Category>> res = new CommonResponse<>();
		
		try {
			List<Category>list = categoryDao.getListOfCategory();
			res.setSuccess(true);
			res.setData(list);
			return res;
		} catch (Exception e) {
			
			res.setSuccess(false);
			res.setMessage("Error occur while getting categories");
			return res;
		}
		
		
	}

	@Override
	public CommonResponse<String> deleteCategory(Long id) {
		CommonResponse<String> response = new CommonResponse<>();
		int res = categoryDao.deleteCategory(id);
		if(res > 0) {
			response.setSuccess(true);
			response.setMessage("Category deleted successfully");
			return response;
		}
		response.setSuccess(false);
		response.setMessage("Error occur while deleting data");
		return response;
	}

}
