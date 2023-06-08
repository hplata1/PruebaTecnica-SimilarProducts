package com.zara.similarproducts.infraestructure.inputadapter.http;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zara.similarproducts.application.inputport.SimilarProductsInputPort;
import com.zara.similarproducts.domain.Product;

@RestController
@RequestMapping("/product/{productId}")
public class SimilarProductsAPI {

    private final SimilarProductsInputPort similarProductsInputPort;

    @Autowired
    public SimilarProductsAPI(SimilarProductsInputPort similarProductsInputPort) {
        this.similarProductsInputPort = similarProductsInputPort;
    }

    @GetMapping(value = "similar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getSimilarProducts(@PathVariable String productId) {
        List<Product> similarProducts = similarProductsInputPort.getInfoSimilarProducts(productId);
        if (similarProducts.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(similarProducts);
        }
    }
}
