package com.example.cartapi.dto;

import com.example.cartapi.model.ProductId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartEvent {

    private Long productId;

    private int quantity;

    private Long userId;


}
