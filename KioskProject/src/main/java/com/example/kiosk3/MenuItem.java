package com.example.kiosk3;

public class MenuItem {
    private final MenuType menuType;
    private final String name;
    private final float price;
    private final String description;

    public MenuItem(MenuType menuType, String name, float price, String description) {
        this.menuType = menuType;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public MenuType getMenuType() { return menuType; }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
