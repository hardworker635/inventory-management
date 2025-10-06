package com.assignment.inventory.inventory_management_system.dto;

// This data transfer object is used as output DTO - get - model's database table schema
public class ProductDTO {

    private Long productId;

    private String sku; // Stock Keeping Unit

    private String productName;

    private String productDesc;

    private Double productPrice;

    private Category productCategory;

    private Integer stockQuantity;

    private Integer lowStockThreshold;

    // Alternatively constructors, getters and setters can be created using Lombok Dependency's annotations

    // No Arguments Constructor
    public ProductDTO(){

    }

    // All Arguments Constructor
    public ProductDTO(Long productId,String sku, String productName,String productDesc, Double productPrice, Category productCategory, Integer stockQuantity, Integer lowStockThreshold){
        this.productId = productId;
        this.sku = sku;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
        this.stockQuantity = stockQuantity;
        this.lowStockThreshold = lowStockThreshold;
    }
    // Getter Method for Product ID
    public Long getProductId() {
        return productId;
    }
    // Setter Method for Product ID
    public void setProductId(Long productId) {
        this.productId = productId;
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
