package com.ecommerce.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.app.apiresponse.CommonResponse;
import com.ecommerce.app.entity.Product;
import com.ecommerce.app.service.WishlistService;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

	@Autowired
	private WishlistService wishlistService;

	@PutMapping("/add/product/{userId}/{productId}")
	public ResponseEntity<CommonResponse<String>> addProductToWishList(@PathVariable Long userId,
			@PathVariable Long productId) {
		return ResponseEntity.ok(wishlistService.addToWishList(userId, productId));
	}

	@GetMapping("/get/products")
	public ResponseEntity<CommonResponse<List<Product>>> getListOfProducts(@RequestParam Long userId) {
		return ResponseEntity.ok(wishlistService.getWishListByUserId(userId));
	}

	@DeleteMapping("/delete/product/{userId}/{productId}")
	public ResponseEntity<CommonResponse<String>> removeProductFromWishlist(@PathVariable Long userId,
			@PathVariable Long productId) {
		return ResponseEntity.ok(wishlistService.removeFromWishlist(userId, productId));
	}

}
