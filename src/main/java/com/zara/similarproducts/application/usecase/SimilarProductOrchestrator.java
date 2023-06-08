package com.zara.similarproducts.application.usecase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zara.similarproducts.application.inputport.SimilarProductsInputPort;
import com.zara.similarproducts.application.outputport.SimilarProductOutPutPort;
import com.zara.similarproducts.domain.Product;

@Component
public class SimilarProductOrchestrator implements SimilarProductsInputPort{

    private SimilarProductOutPutPort similarProductOutPutPort;

    public SimilarProductOrchestrator(SimilarProductOutPutPort similarProductOutPutPort) {
        this.similarProductOutPutPort = similarProductOutPutPort;
    }

    @Override
    public List<Product> getInfoSimilarProducts(String productId) {
        List<Product> descriptions = new ArrayList<>();
        String similarProductIds = similarProductOutPutPort.getSimilarProductIds(productId);
        if (similarProductIds != null) {
            List<String> productIds = parseSimilarProductIds(similarProductIds);
        
            for (String id : productIds) {
                Product description = similarProductOutPutPort.getProductIdsDescription(id);
                if (description != null) {
                    descriptions.add(description);
                }
            }
        
            return descriptions;
        }
        return Collections.emptyList();
    }
    
    private static List<String> parseSimilarProductIds(String similarProductIds) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(similarProductIds, new TypeReference<List<String>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    
}
