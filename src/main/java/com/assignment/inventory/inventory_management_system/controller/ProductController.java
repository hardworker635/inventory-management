package com.assignment.inventory.inventory_management_system.controller;

import com.assignment.inventory.inventory_management_system.dto.CreateProductDTO;
import com.assignment.inventory.inventory_management_system.dto.ProductDTO;
import com.assignment.inventory.inventory_management_system.dto.StockUpdateDTO;
import com.assignment.inventory.inventory_management_system.repository.ProductRepository;
import com.assignment.inventory.inventory_management_system.service.ProductServiceInterface;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    // Constructor Dependency Injection
    private final ProductServiceInterface productServiceInterface;

     public ProductController(ProductServiceInterface productServiceInterface){
         this.productServiceInterface = productServiceInterface;
     }

    // Post Method - To add a product
    @PostMapping
    public ResponseEntity<ProductDTO> addProductEntry(@Valid @RequestBody CreateProductDTO givenProductInfo){

        ProductDTO savedProductDTO = productServiceInterface.createProductEntry(givenProductInfo);
        ResponseEntity<ProductDTO> dtoResponseEntity = new ResponseEntity<>(savedProductDTO,HttpStatus.CREATED);
        return dtoResponseEntity;

    }

    // get all products
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProductEntries(){
        List<ProductDTO> listProductEntries = productServiceInterface.retrieveAllProductEntries();
        return new ResponseEntity<>(listProductEntries,HttpStatus.OK);
        // even if product list is empty - []
    }



    // find by Product Id
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Long productId){

        ResponseEntity<ProductDTO> obj = new ResponseEntity<>(productServiceInterface.retrieveProductById(productId),HttpStatus.OK);
        return obj;


    }

    // find by sku - used by shop owner
    // sku is unique
    @GetMapping("/sku/{sku}")
    public ResponseEntity<ProductDTO> getProductBySku(@PathVariable("sku") String sku){

        ResponseEntity<ProductDTO> obj = new ResponseEntity<>(productServiceInterface.retrieveProductBySku(sku),HttpStatus.OK);
        return obj;


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable("id") Long id){

        productServiceInterface.deleteProductEntryById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    // delete by sku - for shop owner
    @DeleteMapping("/sku/{sku}")
    public ResponseEntity<Void> deleteProductBySku(@PathVariable("sku") String sku){

        productServiceInterface.deleteProductEntryBySku(sku);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    // patch mapping - by product id // update any of the fields of product
    @PatchMapping("/{id}")
    public ResponseEntity<ProductDTO> updatePartialProductEntryById(@PathVariable("id") Long id, @RequestBody Map<String,Object> payload){

        ProductDTO returnedProd = productServiceInterface.modifyPartialProductById(id,payload);
        return new ResponseEntity<>(returnedProd,HttpStatus.OK);

    }
    // Put Request - change all fields by product ID
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> modifyEntireProductById(@PathVariable("id") Long id, @Valid @RequestBody CreateProductDTO inputProduct){
        return new ResponseEntity<>(productServiceInterface.modifyEntireProduct(id,inputProduct),HttpStatus.OK);
    }

    // Increase Stock quantity - patch Request
    @PatchMapping("/stock/increase")
    public ResponseEntity<ProductDTO> increaseStockQuantityById(@RequestParam("id") Long id, @Valid @RequestBody StockUpdateDTO payload){
        return new ResponseEntity<>(productServiceInterface.increaseStockQuantity(id,payload),HttpStatus.OK);

    }
    // Decrease Stock Quantity
    @PatchMapping("/stock/decrease")
    public ResponseEntity<ProductDTO> decreaseStockQuantityById(@RequestParam("id") Long id, @Valid @RequestBody StockUpdateDTO payload){
        return new ResponseEntity<>(productServiceInterface.decreaseStockQuantity(id,payload),HttpStatus.OK);

    }
    // get all the products whose stock < threshold
    @GetMapping("stock/low")
    public ResponseEntity<List<ProductDTO>> getAllLowStockProductEntries(){
        return new ResponseEntity<>(productServiceInterface.retrieveAllLowStockProductEntries(),HttpStatus.OK);
    }





}
