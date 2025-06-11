package com.ecommerce.app.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.app.apiresponse.CommonResponse;
import com.ecommerce.app.dao.WishListDao;
import com.ecommerce.app.entity.Product;
import com.ecommerce.app.service.WishlistService;


@Service
public class WishListServiceImpl implements WishlistService {

	@Autowired
	private WishListDao wishListDao;

	@Override
	public CommonResponse<String> addToWishList(Long userId, Long productId) {
		CommonResponse<String> response = new CommonResponse<>();
		Integer result = wishListDao.addToWishlist(userId, productId);
		if (result == 1) {
			response.setSuccess(true);
			response.setMessage("Product added to wishlist successfully");
			return response;
		}
		response.setSuccess(false);
		response.setMessage("Error occured while adding product to the wishlist");
		return response;
	}

	@Override
	public CommonResponse<List<Product>> getWishListByUserId(Long userid) {
		CommonResponse<List<Product>> response = new CommonResponse<>();
		List<Product> result = wishListDao.getWishlistByUserId(userid);

		response.setSuccess(true);
		response.setMessage("Wishlist fetched successfully");
		response.setData(result);
		return response;
	}

	@Override
	public CommonResponse<String> removeFromWishlist(Long userId, Long productId) {
		CommonResponse<String> response = new CommonResponse<>();
		Integer result = wishListDao.removeFromWishlist(userId, productId);
		if (result == 1) {
			response.setSuccess(true);
			response.setMessage("Product removed from wishlist successfully");
			return response;
		}
		response.setSuccess(false);
		response.setMessage("Error occured while removing product from wishlist");
		return response;
	}

}
