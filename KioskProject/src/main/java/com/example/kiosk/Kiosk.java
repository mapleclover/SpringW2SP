package com.example.kiosk;

import java.util.List;

public class Kiosk {
    Menu menu = new Menu();

    //키오스크에 메뉴추가를 하는 초기화
    public void initialize() {
        menu.addMenu(MenuType.BURGERS, new MenuItem("ShackBurger", 6.9f, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        menu.addMenu(MenuType.BURGERS, new MenuItem("SmokeShack", 8.9f, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        menu.addMenu(MenuType.BURGERS, new MenuItem("Cheeseburger", 6.9f, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        menu.addMenu(MenuType.BURGERS, new MenuItem("Hamburger", 5.4f, "비프패티를 기반으로 야채가 들어간 기본버거"));
    }

    //메뉴를 출력하는 키오스크
    public void showBurgerMenu() {
        System.out.println("[  SHAKESHACK MENU  ]");
        List<MenuItem> burgerMenu = menu.getBurgers();
        MenuItem burger;
        for (int i = 0; i < burgerMenu.size(); i++) {
            burger = burgerMenu.get(i);
            System.out.printf("%d. %-15s | W %.1f | %s \n" , i+1, burger.getName(), burger.getPrice(), burger.getDescription());
        }
        System.out.println("0. 종료");
    }
}


