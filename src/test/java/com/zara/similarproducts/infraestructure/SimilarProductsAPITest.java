package com.zara.similarproducts.infraestructure;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zara.similarproducts.application.inputport.SimilarProductsInputPort;
import com.zara.similarproducts.domain.Product;
import com.zara.similarproducts.infraestructure.inputadapter.http.SimilarProductsAPI;

@ExtendWith(MockitoExtension.class)
public class SimilarProductsAPITest {

    @Mock
    private SimilarProductsInputPort  similarProductsInputPort;
    
    @InjectMocks
    private SimilarProductsAPI similarProductsAPI;

    @Test
    public void getSimilarProductsOKTest() throws JsonProcessingException {

        String productId = "1";

        List<Product> similarProducts = new ArrayList<>();

        Product product1 = new Product();
        product1.setId("2");
        product1.setName("Dress");
        product1.setPrice(19.99);
        product1.setAvailability("true");

        Product product2 = new Product();
        product2.setId("3");
        product2.setName("Blazer");
        product2.setPrice(29.99);
        product2.setAvailability("false");

        Product product3 = new Product();
        product3.setId("4");
        product3.setName("Boots");
        product3.setPrice(39.99);
        product3.setAvailability("true");

        similarProducts.add(product1);
        similarProducts.add(product2);
        similarProducts.add(product3);

        when(similarProductsInputPort.getInfoSimilarProducts(productId)).thenReturn(similarProducts);

        ResponseEntity<List<Product>> response = similarProductsAPI.getSimilarProducts(productId);
        
        Assertions.assertAll(
                () -> Assertions.assertNotNull(response)
        );
    }

    @Test
    public void getSimilarProductsFailedTest() throws JsonProcessingException {

        String productId = "1";

        List<Product> similarProducts = new ArrayList<>();

        when(similarProductsInputPort.getInfoSimilarProducts(productId)).thenReturn(similarProducts);

        ResponseEntity<List<Product>> response = similarProductsAPI.getSimilarProducts(productId);
        HttpStatus responseStatus = (HttpStatus) response.getStatusCode();
        Assertions.assertAll(
                () -> Assertions.assertEquals(HttpStatus.NOT_FOUND.toString(), responseStatus.toString())

        );
    }
    
}
