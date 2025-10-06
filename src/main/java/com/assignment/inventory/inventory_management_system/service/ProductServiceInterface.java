package com.assignment.inventory.inventory_management_system.service;

import com.assignment.inventory.inventory_management_system.dto.CreateProductDTO;
import com.assignment.inventory.inventory_management_system.dto.ProductDTO;
import com.assignment.inventory.inventory_management_system.dto.StockUpdateDTO;
import jakarta.validation.Valid;
import org.hibernate.procedure.ProcedureOutputs;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductServiceInterface {

    // abstract methods are provided here - implemented by Service classes
    ProductDTO createProductEntry(CreateProductDTO givenProductDTO);

    List<ProductDTO> retrieveAllProductEntries();


    ProductDTO retrieveProductById(Long id);

    void deleteProductEntryById(Long id);

    ProductDTO retrieveProductBySku(String sku);

    void deleteProductEntryBySku(String sku);

    ProductDTO modifyPartialProductById(Long id, Map<String,Object> payload);

    ProductDTO increaseStockQuantity(Long id, StockUpdateDTO payload);

    ProductDTO decreaseStockQuantity(Long id, StockUpdateDTO payload);

    ProductDTO modifyEntireProduct(Long id, CreateProductDTO input);

    List<ProductDTO> retrieveAllLowStockProductEntries();
}
