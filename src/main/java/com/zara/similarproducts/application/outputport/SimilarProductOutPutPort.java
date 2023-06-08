package com.zara.similarproducts.application.outputport;

import com.zara.similarproducts.domain.Product;

public interface SimilarProductOutPutPort {

        String  getSimilarProductIds(String productId);
        Product getProductDescription(String productId);

}
