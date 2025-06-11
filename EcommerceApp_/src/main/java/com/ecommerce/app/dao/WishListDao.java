package com.ecommerce.app.dao;

import java.util.List;

import com.ecommerce.app.entity.Product;

public interface WishListDao {
	
	Integer addToWishlist(Long userId, Long productId);
    List<Product> getWishlistByUserId(Long userId);
    Integer removeFromWishlist(Long userId, Long productId);

}
