package com.zara.similarproducts.application.inputport;

import java.util.List;

import com.zara.similarproducts.domain.Product;

public interface SimilarProductsInputPort {

    public List<Product> getInfoSimilarProducts(String productId);
    
}
