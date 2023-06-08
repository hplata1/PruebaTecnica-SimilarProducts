package com.zara.similarproducts.infraestructure.outputadapter.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.zara.similarproducts.application.outputport.SimilarProductOutPutPort;
import com.zara.similarproducts.domain.Product;

@Component
public class ProductAdapterRest implements SimilarProductOutPutPort {

    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ProductAdapterRest.class);


    @Value("${apiproduct.url}")
    private String productApiUrl;

    public ProductAdapterRest() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public String getSimilarProductIds(String productId) {
        String url = productApiUrl+productId+"/similarids";
        try {
            return restTemplate.getForObject(url, String.class, productId);
        } catch (RestClientException e) {
            logger.info("Failed to call similar product API: {}. ProductId: {}", e.getMessage(),productId);
            return null;
        }
    }

    @Override
    public Product getProductDescription(String productId) {
        String url = productApiUrl+productId;
        try {
            return restTemplate.getForObject(url, Product.class, productId);
        } catch (RestClientException e) {
            logger.info("Failed to call detail product API: {}. ProductId: {}", e.getMessage(), productId);
            return null;
        }
    }
    
}
