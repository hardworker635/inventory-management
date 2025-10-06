package com.assignment.inventory.inventory_management_system.dto;
import jakarta.validation.constraints.*;
// This data transfer Object is used for input - POST,PUT AND PATCH
public class CreateProductDTO {

    @NotBlank(message = "SKU is required and needs to be a valid string")
    @Size(min=2,max = 60)
    private String sku; // Stock Keeping Unit

    @NotBlank(message = "Product name is required and needs to be a valid string")
    private String productName;

    // product description is an optional field
    @Size(max=400,message = "Description can't exceed 400 characters")
    private String productDesc;

    @NotNull(message = "Product price is mandatory")
    @Positive // only positive values
    private Double productPrice;

    @NotNull(message = "Category is a mandatory field")
    private Category productCategory;

    @NotNull(message = "Stock Quantity is mandatory")
    @PositiveOrZero
    private Integer stockQuantity;

    @NotNull(message = "Low Stock Threshold is a mandatory field")
    @PositiveOrZero
    private Integer lowStockThreshold;

    // Alternatively constructors, getters and setters can be created using Lombok Dependency's annotations

    // No Arguments Constructor
    public CreateProductDTO(){

    }

    // All Arguments Constructor
    public CreateProductDTO(String sku, String productName,String productDesc, Double productPrice, Category productCategory, Integer stockQuantity, Integer lowStockThreshold){
        this.sku = sku;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
        this.stockQuantity = stockQuantity;
        this.lowStockThreshold = lowStockThreshold;
    }
    // Getter Method for Product SKU
    public String getSku() {
        return sku;
    }
    // Setter Method for Product SKU
    public void setSku(String sku) {
        this.sku = sku;
    }
    // Getter Method for Product Name
    public String getProductName() {
        return productName;
    }
    // Setter Method for Product Name
    public void setProductName(String productName) {
        this.productName = productName;
    }

    // Getter Method for Product Description
    public String getProductDesc() {
        return productDesc;
    }
    // Setter Method for Product Description
    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    // Getter Method for Product Price
    public Double getProductPrice() {
        return productPrice;
    }
    // Setter Method for Product Price
    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    // Getter Method for Product Category
    public Category getProductCategory() {
        return productCategory;
    }

    // Setter Method for Product Category
    public void setProductCategory(Category productCategory) {
        this.productCategory = productCategory;
    }

    // Getter Method for Stock Quantity
    public Integer getStockQuantity() {
        return stockQuantity;
    }

    // Setter Method for Stock Quantity
    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    // Getter Method for Low Stock Threshold
    public Integer getLowStockThreshold() {
        return lowStockThreshold;
    }
    // Setter Method for Low Stock Threshold
    public void setLowStockThreshold(Integer lowStockThreshold) {
        this.lowStockThreshold = lowStockThreshold;
    }
}
