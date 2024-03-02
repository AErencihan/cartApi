package com.example.cartapi.service;


import com.example.cartapi.model.Cart;
import com.example.cartapi.model.CartItem;
import com.example.cartapi.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;


    @KafkaListener(topics = "topicProduct", groupId = "group_Id")
    public Cart addProductToCart(Long userId, CartItem cartItem) {
        Cart cart = cartRepository.findByUserId(userId).orElse(Cart.builder()
                .userId(userId)
                .build());

        Optional<CartItem> existingProductId = cart.getCartItemsId().stream()
                .filter(i -> i.getProductId().equals(cartItem.getProductId()))
                .findFirst();

        if (existingProductId.isPresent()) {
            CartItem existingCartItem = existingProductId.get();
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItem.getQuantity());
        } else {
            cart.getCartItemsId().add(cartItem);
        }
        return cartRepository.save(cart);

    }


}
