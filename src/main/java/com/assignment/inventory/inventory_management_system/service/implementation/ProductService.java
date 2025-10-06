package com.assignment.inventory.inventory_management_system.service.implementation;

import com.assignment.inventory.inventory_management_system.dto.Category;
import com.assignment.inventory.inventory_management_system.dto.CreateProductDTO;
import com.assignment.inventory.inventory_management_system.dto.ProductDTO;
import com.assignment.inventory.inventory_management_system.dto.StockUpdateDTO;
import com.assignment.inventory.inventory_management_system.entity.Product;
import com.assignment.inventory.inventory_management_system.repository.ProductRepository;
import com.assignment.inventory.inventory_management_system.service.ProductServiceInterface;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService implements ProductServiceInterface {

    // Constructor Dependency Injection
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, ModelMapper modelMapper){
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ProductDTO createProductEntry(CreateProductDTO givenProductDTO) {
        // convert the CreateProductDTO to Product Entity
        // capitalize the sku - add it to table
        String upperSKU = givenProductDTO.getSku().toUpperCase();
        Product newProduct = new Product(upperSKU, givenProductDTO.getProductName(),givenProductDTO.getProductDesc(),givenProductDTO.getProductPrice(),givenProductDTO.getProductCategory(),givenProductDTO.getStockQuantity(),givenProductDTO.getLowStockThreshold());
        Product savedProduct = productRepository.save(newProduct);
        // convert the savedProduct to ProductDTO
        ProductDTO reqProductDTO = modelMapper.map(savedProduct,ProductDTO.class);
        return reqProductDTO;
    }

    @Override
    public List<ProductDTO> retrieveAllProductEntries() {
        List<Product> allProducts =  productRepository.findAll();
        List<ProductDTO> returnedProducts = new ArrayList<>();

        for(Product p: allProducts){
            ProductDTO req = modelMapper.map(p,ProductDTO.class);
            returnedProducts.add(req);
        }
        return returnedProducts;
    }

    @Override
    public ProductDTO retrieveProductById(Long id) {
        // findById - returns Optional
        Product prod = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Product not found with Id " + id));
        ProductDTO reqDTO = modelMapper.map(prod,ProductDTO.class);
        return reqDTO;
    }

    @Override
    public void deleteProductEntryById(Long id) {
        // if Id doesn't exist - throw exception
        if(!productRepository.existsById(id)){
            throw new NoSuchElementException("Product does not exist with id " + id);

        }
        productRepository.deleteById(id); // returning void
    }

    @Override
    public ProductDTO retrieveProductBySku(String sku) {
        // convert the given sku to uppercase
        String convertedSku = sku.toUpperCase();
        // findById - returns Optional
        Product prod = productRepository.findBySku(convertedSku).orElseThrow(() -> new NoSuchElementException("Product not found with sku " + sku));
        ProductDTO reqDTO = modelMapper.map(prod,ProductDTO.class);
        return reqDTO;
    }

    @Transactional
    @Override
    public void deleteProductEntryBySku(String sku) {
        String convertedSku = sku.toUpperCase();
        System.out.println(convertedSku);
        System.out.println(productRepository.existsBySku(convertedSku));
        if(!productRepository.existsBySku(convertedSku)){
            throw new NoSuchElementException("Product does not exist with sku " + sku);
        }
        productRepository.deleteBySku(convertedSku);
    }

    @Override
    public ProductDTO modifyPartialProductById(Long id, Map<String, Object> payload) {

        // check if id exists
        Product oldProd = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Product not found with Id " + id));
        for (Map.Entry<String, Object> entry : payload.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            switch (key) {
                case "sku":
                    if (value instanceof String str && !str.isBlank()) {
                        if (str.length() > 60 || str.length() < 2) {
                            throw new IllegalArgumentException("SKU should be between 2 and 60 characters");
                        }
                        oldProd.setSku(str.toUpperCase());
                    } else {
                        throw new IllegalArgumentException("SKU must be a non-blank string.");
                    }
                    break;

                case "productName":
                    if (value instanceof String str && !str.isBlank()) {
                        oldProd.setProductName(str);
                    } else {
                        throw new IllegalArgumentException("Product name must be a non-blank string.");
                    }
                    break;

                case "productDesc":
                    if (value instanceof String str) {
                        if (str.length() > 400) {
                            throw new IllegalArgumentException("Description can't exceed 400 characters.");
                        }
                        oldProd.setProductDesc(str);
                    } else {
                        throw new IllegalArgumentException("Product description must be a string.");
                    }
                    break;

                case "productPrice":
                    if (value instanceof Number) {
                        double price = ((Number) value).doubleValue();
                        if (price <= 0) {
                            throw new IllegalArgumentException("Product price must be greater than 0.");
                        }
                        oldProd.setProductPrice(price);
                    } else {
                        throw new IllegalArgumentException("Product price must be a number.");
                    }
                    break;

                case "productCategory":
                    if (value instanceof String str) {
                        try {
                            Category category = Category.valueOf(str.toUpperCase());
                            oldProd.setProductCategory(category);
                        } catch (IllegalArgumentException e) {
                            throw new IllegalArgumentException("Invalid category. Allowed values: " +
                                    Arrays.toString(Category.values()));
                        }
                    } else {
                        throw new IllegalArgumentException("Category must be a string.");
                    }
                    break;

                case "stockQuantity":
                    if (value instanceof Number) {
                        int stock = ((Number) value).intValue();
                        if (stock < 0) {
                            throw new IllegalArgumentException("Stock quantity cannot be negative.");
                        }
                        oldProd.setStockQuantity(stock);
                    } else {
                        throw new IllegalArgumentException("Stock quantity must be a number.");
                    }
                    break;

                case "lowStockThreshold":
                    if (value instanceof Number) {
                        int lowStock = ((Number) value).intValue();
                        if (lowStock < 0) {
                            throw new IllegalArgumentException("Low Stock Threshold cannot be negative.");
                        }
                        oldProd.setLowStockThreshold(lowStock);
                    } else {
                        throw new IllegalArgumentException("Low Stock Threshold must be a number");
                    }
                    break;

                default:
                    throw new IllegalArgumentException("Field '" + key + "' is not allowed for update.");
            }
        }


        Product newProd = productRepository.save(oldProd);
        ProductDTO reqDTO = modelMapper.map(newProd,ProductDTO.class);
        return reqDTO;
    }

        @Override
        public ProductDTO increaseStockQuantity(Long id, StockUpdateDTO payload) {
            // if Id is present or  not
            Product oldProd = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Product not found with Id " + id));
            int currStock = oldProd.getStockQuantity();
            oldProd.setStockQuantity(currStock + payload.getAmount());
            Product updatedStock = productRepository.save(oldProd);
            ProductDTO req = modelMapper.map(updatedStock,ProductDTO.class);
            return req;
        }

    @Override
    public ProductDTO decreaseStockQuantity(Long id, StockUpdateDTO payload) {
        // if Id is present or not
        // if Id is present or  not
        Product oldProd = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Product not found with Id " + id));
        int currStock = oldProd.getStockQuantity();
        if(currStock - payload.getAmount() < 0){
            throw new IllegalArgumentException("Insufficient Stock, stock amount requested is unavailable");
        }
        oldProd.setStockQuantity(currStock - payload.getAmount());
        Product updatedStock = productRepository.save(oldProd);
        ProductDTO req = modelMapper.map(updatedStock,ProductDTO.class);
        return req;
    }

    @Override
    public ProductDTO modifyEntireProduct(Long id, CreateProductDTO input) {

        // check if Id is present
        Product oldProd = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Product not found with Id " + id));
        oldProd.setAllFields(input.getSku().toUpperCase(),input.getProductName(),input.getProductDesc(),input.getProductPrice(),input.getProductCategory(),input.getStockQuantity(), input.getLowStockThreshold());
        Product savedProd = productRepository.save(oldProd);
        return modelMapper.map(savedProd,ProductDTO.class);
    }

    @Override
    public List<ProductDTO> retrieveAllLowStockProductEntries() {

        List<Product> returnedList = productRepository.findAllBelowStockThreshold();
        List<ProductDTO> returnedProducts = new ArrayList<>();

        for(Product p: returnedList){
            ProductDTO req = modelMapper.map(p,ProductDTO.class);
            returnedProducts.add(req);
        }
        return returnedProducts;
    }


}
