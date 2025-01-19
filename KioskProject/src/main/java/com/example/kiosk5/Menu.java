package com.example.kiosk5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Menu {
    private List<MenuItem> menuItems = new ArrayList<>();

    public void setMenuItems(MenuItem menuItem){
        menuItems.add(menuItem);
    }

    public List<MenuItem> getMenuItems() {
        return new ArrayList<>(menuItems);
    }

    public List<MenuItem> getBurgers() {
        return menuItems.stream().filter(item -> item.getMenuType() == MenuType.BURGERS).collect(Collectors.toList());
    }

    public List<MenuItem> getDrinks() {
        return menuItems.stream().filter(item -> item.getMenuType() == MenuType.DRINKS).collect(Collectors.toList());
    }

    public List<MenuItem> getDesserts() {
        return menuItems.stream().filter(item -> item.getMenuType() == MenuType.DESSERTS).collect(Collectors.toList());
    }
}
