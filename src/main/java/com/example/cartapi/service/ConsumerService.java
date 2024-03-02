package com.example.cartapi.service;

import com.example.cartapi.dto.CartEvent;
import com.example.cartapi.model.CartItem;
import com.example.cartapi.model.ProductId;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsumerService {

    private final ObjectMapper objectMapper;
    private final CartService cartService;


    @KafkaListener(topics = "topicProduct", groupId = "group_Id")
    public void createCart(String message) {
        try {
            log.info("Consumed message: {}", message);
            CartEvent cartEvent = objectMapper.readValue(message, CartEvent.class);

            CartItem cartItem = new CartItem();
            cartItem.setProductId(ProductId.builder()
                    .productId(cartEvent.getProductId())
                    .quantity(cartEvent.getQuantity())
                    .build());
            cartService.addProductToCart(cartEvent.getUserId(), cartItem);

            log.info("Product saved in Elasticsearch: {}", cartItem);
        } catch (Exception e) {
            log.error("Error consuming message: {}", e.getMessage());
        }
    }


}
