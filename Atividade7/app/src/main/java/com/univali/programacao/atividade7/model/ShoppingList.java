package com.univali.programacao.atividade7.model;

import android.content.Intent;

public class ShoppingList {

    private Integer id;
    private String product;
    private String type;
    private String quantity;
    private Boolean purchased;

    public ShoppingList() {
    }

    public ShoppingList(Integer id, String product, String type, String quantity, Boolean purchased) {
        this.id = id;
        this.product = product;
        this.type = type;
        this.quantity = quantity;
        this.purchased = purchased;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPurchased(Boolean purchased) {
        this.purchased = purchased;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Boolean getPurchased() {
        return purchased;
    }
}
