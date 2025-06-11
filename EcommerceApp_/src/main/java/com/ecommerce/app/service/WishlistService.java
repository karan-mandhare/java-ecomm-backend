package com.ecommerce.app.service;

import java.util.List;

import com.ecommerce.app.apiresponse.CommonResponse;
import com.ecommerce.app.entity.Product;

public interface WishlistService {
	
	public CommonResponse<String> addToWishList(Long userId, Long productId);
	public CommonResponse<List<Product>> getWishListByUserId(Long userid);
	public CommonResponse<String> removeFromWishlist(Long userId, Long productId);

}
