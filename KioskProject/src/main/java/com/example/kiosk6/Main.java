package com.example.kiosk6;


public class Main {
    public static void main(String[] args) {
        //키오스크 객채화 및 초기화
        Kiosk kiosk = new Kiosk(
                new MenuItem(MenuType.BURGERS, "ShackBurger", 6.9f, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"),
                new MenuItem(MenuType.BURGERS, "SmokeShack", 8.9f, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"),
                new MenuItem(MenuType.BURGERS, "Cheeseburger", 6.9f, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"),
                new MenuItem(MenuType.BURGERS, "Hamburger", 5.4f, "비프패티를 기반으로 야채가 들어간 기본버거")
        );
        kiosk.start();

        System.out.println("프로그램을 종료합니다.");
    }
}
