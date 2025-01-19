package com.example.kiosk3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Menu {
    private final List<MenuItem> menuItems = new ArrayList<>();

    public void addMenuItem(MenuItem menuItem){
        menuItems.add(menuItem);
    }

    public List<MenuItem> getMenuItems() {
        return new ArrayList<MenuItem>(menuItems);
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
