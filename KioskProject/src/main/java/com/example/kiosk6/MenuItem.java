package com.example.kiosk6;

public class MenuItem{
    MenuType menuType;
    private String name;
    private float price;
    private String description;

    public MenuItem(MenuType menuType, String name, float price, String description) {
        this.menuType = menuType;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

}
