package com.example.kiosk6;

public class ShoppingCartItem extends MenuItem {
    private int count;

    public ShoppingCartItem(MenuType menuType, String name, float price, String description) {
        super(menuType, name, price, description);
        this.count = 1;
    }

    public int getCount() {
        return count;
    }

    public void addCount() {
        count++;
    }
}
