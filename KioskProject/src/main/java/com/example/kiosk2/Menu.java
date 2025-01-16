package com.example.kiosk2;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final List<MenuItem> burgers = new ArrayList<>();
    private final List<MenuItem> drinks = new ArrayList<>();
    private final List<MenuItem> desserts = new ArrayList<>();

    //메뉴를 분류하여 넣어주는 함수
    public void addMenu(MenuType menuType, MenuItem menuItem){
        switch (menuType) {
            case BURGERS:
                burgers.add(menuItem);
                break;
            case DRINKS:
                drinks.add(menuItem);
                break;
            case DESSERTS:
                desserts.add(menuItem);
                break;
            default:
                throw new IllegalArgumentException("제공하지 않는 메뉴 타입입니다.");
        }
    }

    public List<MenuItem> getBurgers() {
        return new ArrayList<>(burgers);
    }

    public List<MenuItem> getDrinks() {
        return new ArrayList<>(drinks);
    }

    public List<MenuItem> getDesserts() {
        return new ArrayList<>(desserts);
    }
}
