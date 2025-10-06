package com.assignment.inventory.inventory_management_system.entity;

import com.assignment.inventory.inventory_management_system.dto.Category;
import jakarta.persistence.*;

@Entity // From ORM mapping - creates a table called Product
public class Product {

    @Id // primary key for table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // generates a unique product id from 1 - every time a post request is called
    private Long productId;

    @Column(unique = true, nullable = false)
    private String sku; // Stock Keeping Unit

    @Column(nullable = false)
    private String productName;

    private String productDesc;

    @Column(nullable = false)
    private Double productPrice;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING) // ensures data is stored in String format and not ordinal format
    private Category productCategory;

    @Column(nullable = false)
    private Integer stockQuantity;

    @Column(nullable = false)
    private Integer lowStockThreshold;

    // No Arguments Constructor
    public Product(){

    }

    // All Arguments Constructor
    public Product(Long productId,String sku, String productName,String productDesc, Double productPrice, Category productCategory, Integer stockQuantity, Integer lowStockThreshold){
        this.productId = productId;
        this.sku = sku;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
        this.stockQuantity = stockQuantity;
        this.lowStockThreshold = lowStockThreshold;
    }
    // constructor without Product Id - automatically generated
    public Product(String sku, String productName,String productDesc, Double productPrice, Category productCategory, Integer stockQuantity, Integer lowStockThreshold){
        this.sku = sku;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
        this.stockQuantity = stockQuantity;
        this.lowStockThreshold = lowStockThreshold;
    }
    // modify all the fields except id
    public void setAllFields(String sku, String productName,String productDesc, Double productPrice, Category productCategory, Integer stockQuantity, Integer lowStockThreshold){
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
    // Setter  Method for Low Stock Threshold
    public void setLowStockThreshold(Integer lowStockThreshold) {
        this.lowStockThreshold = lowStockThreshold;
    }
}
