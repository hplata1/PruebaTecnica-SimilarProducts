package com.zara.similarproducts.application;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zara.similarproducts.application.outputport.SimilarProductOutPutPort;
import com.zara.similarproducts.application.usecase.SimilarProductOrchestrator;
import com.zara.similarproducts.domain.Product;

@ExtendWith(MockitoExtension.class)
public class SimilarProductOrchestratorTest {

    @Mock
    SimilarProductOutPutPort similarProductOutPutPort;

    @InjectMocks
    SimilarProductOrchestrator similarProductOrchestrator;

    @Test
    public void getDetailProductIdNoFoundTest() throws JsonProcessingException {

        String productId = "1";
        String similarProductIds = "[2,3,4]";

        List<Product> DetailProducts = new ArrayList<>();

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

        DetailProducts.add(product1);
        DetailProducts.add(product2);
        DetailProducts.add(product3);

        when(similarProductOutPutPort.getSimilarProductIds(productId)).thenReturn(similarProductIds);
        when(similarProductOutPutPort.getProductIdsDescription("2")).thenReturn(product1);
        when(similarProductOutPutPort.getProductIdsDescription("3")).thenReturn(product2);
        when(similarProductOutPutPort.getProductIdsDescription("4")).thenReturn(null);

        List<Product> response = similarProductOrchestrator.getInfoSimilarProducts(productId);
        
        Assertions.assertAll(
                () -> Assertions.assertNotNull(response),
                () -> Assertions.assertFalse(response.isEmpty())
        );
        
    }


    @Test
    public void getSimilarProductIdNoFoundTest() throws JsonProcessingException {

        String productId = "1";
        String similarProductIds = null;

        when(similarProductOutPutPort.getSimilarProductIds(productId)).thenReturn(similarProductIds);
        
        List<Product> response = similarProductOrchestrator.getInfoSimilarProducts(productId);
        
        Assertions.assertAll(
                () -> Assertions.assertTrue(response.isEmpty())
        );
        
    }
    

    @Test
    public void getInfoSimilarProductsOKTest() throws JsonProcessingException {

        String productId = "1";
        String similarProductIds = "[2,3,4]";

        List<Product> DetailProducts = new ArrayList<>();

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

        DetailProducts.add(product1);
        DetailProducts.add(product2);
        DetailProducts.add(product3);

        when(similarProductOutPutPort.getSimilarProductIds(productId)).thenReturn(similarProductIds);
        when(similarProductOutPutPort.getProductIdsDescription("2")).thenReturn(product1);
        when(similarProductOutPutPort.getProductIdsDescription("3")).thenReturn(product2);
        when(similarProductOutPutPort.getProductIdsDescription("4")).thenReturn(product3);

        List<Product> response = similarProductOrchestrator.getInfoSimilarProducts(productId);
        
        Assertions.assertAll(
                () -> Assertions.assertNotNull(response),
                () -> Assertions.assertFalse(response.isEmpty())
        );
        
    }

    
}
