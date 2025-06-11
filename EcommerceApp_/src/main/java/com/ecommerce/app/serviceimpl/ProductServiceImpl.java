package com.ecommerce.app.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.app.apiresponse.CommonResponse;
import com.ecommerce.app.dao.ProductDao;
import com.ecommerce.app.entity.Product;
import com.ecommerce.app.request.ProductRequest;
import com.ecommerce.app.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

	@Override
	public CommonResponse<String> addProduct(ProductRequest request) {
		CommonResponse<String> response = new CommonResponse<>();
		try {
			Product product = new Product();
			product.setName(request.getName());
			product.setBrandName(request.getBrandName());
			product.setDesc(request.getDesc());
			product.setPrice(request.getPrice());
			product.setDiscount(request.getDiscount());
			product.setRating(request.getRating());
			product.setAvailable(request.getAvailable());
			product.setImage(request.getImage().getBytes());
			Long categoryId = request.getCategoryId();
			int res = productDao.addProduct(product, categoryId);
			if (res > 0) {
				response.setSuccess(true);
				response.setMessage("Product added successfully");
				return response;
			}
			response.setSuccess(false);
			response.setMessage("Error while adding product");
			return response;
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage("Error while adding product");
			return response;
		}

	}

	@Override
	public CommonResponse<List<Product>> getProducts() {
		CommonResponse<List<Product>> res = new CommonResponse<>();
		List<Product> list = productDao.getProducts();
		res.setSuccess(true);
		res.setData(list);
		return res;
	}

	@Override
	public CommonResponse<List<Product>> getProductByCategory(Long id) {
		CommonResponse<List<Product>> res = new CommonResponse<>();
		List<Product> list = productDao.getProductsByCategory(id);
		res.setSuccess(true);
		res.setData(list);
		return res;
	}

	@Override
	public CommonResponse<Product> deleteProductById(Long id) {
		CommonResponse<Product> res = new CommonResponse<>();
		int result = productDao.deleteProductById(id);
		if(result > 0) {
			res.setSuccess(true);
			res.setMessage("Product deleted successfully");
			return res;
		}
		res.setSuccess(false);
		res.setMessage("Something went wrong while delete product.");
		return res;
	}

}
