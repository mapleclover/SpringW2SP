package com.example.kiosk3;

import java.util.List;
import java.util.Scanner;

public class Kiosk {
    Menu menu = new Menu();

    public Kiosk (MenuItem... menuItems) {
        for (MenuItem menuItem : menuItems) {
            menu.addMenuItem(menuItem);
        }
    }

    //키오스크 로직
    public void start()
    {
        Scanner sc = new Scanner(System.in);
        int input;

        while(true)
        {
            showBurgerMenu(); //메뉴 보여주기

            input  = getValidInput(sc); //Input 유효성 검사

            if(input == 0){
                break;  //0을 입력받으면 종료
            } else {
                showSelectedBurgerMenu(input-1);
            }
        }
    }

    //메뉴를 출력하는 키오스크
    public void showBurgerMenu() {
        System.out.println("[  SHAKESHACK MENU  ]");
        List<MenuItem> burgerMenu = menu.getBurgers(); //버거 메뉴 전체 가져오기
        MenuItem burger; //버거 하나씩 담을 변수 선언
        for (int i = 0; i < burgerMenu.size(); i++) {
            burger = burgerMenu.get(i);
            System.out.printf("%d. %-15s | W %.1f | %s \n" , i+1, burger.getName(), burger.getPrice(), burger.getDescription());
        }
        System.out.println("0. 종료");
    }

    //선택한 버거 메뉴를 알림하는 키오스크
    public void showSelectedBurgerMenu(int index){
        if(menu.getBurgers().size() <= index){
            System.out.println("존재하지 않는 메뉴입니다.");
            return;
        }
        MenuItem burger = menu.getBurgers().get(index);
        System.out.printf("선택한 메뉴 : %s 는 %s 입니다. 가격은 W %.1f 입니다.\n", burger.getName(), burger.getDescription(), burger.getPrice());
    }

    //입력 유효성 검사
    private int getValidInput(Scanner sc){
        while(!sc.hasNextInt())
        {
            sc.next();
            System.out.println("잘못 입력하셨습니다. 숫자를 입력하세요.");
        }
        return sc.nextInt();
    }
}


