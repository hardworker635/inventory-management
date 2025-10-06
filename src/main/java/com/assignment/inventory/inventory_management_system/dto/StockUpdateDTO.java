package com.assignment.inventory.inventory_management_system.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class StockUpdateDTO {

    @NotNull(message = "Amount field to update Stock is required")
    @Positive
    private Integer amount;

    // All Arguments Constructor
    public StockUpdateDTO(Integer amount){
        this.amount = amount;
    }

    // no args constructor
    public StockUpdateDTO(){

    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
