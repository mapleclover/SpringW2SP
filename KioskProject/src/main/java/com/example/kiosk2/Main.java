package com.example.kiosk2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int input;

        //키오스크 객채화 및 초기화
        Kiosk kiosk = new Kiosk();
        kiosk.initialize();

        Scanner sc = new Scanner(System.in);

        while(true)
        {
            kiosk.showBurgerMenu(); //메뉴 보여주기

            input  = getValidInput(sc); //Input 유효성 검사

            switch (input) {
                case 1:
                case 2:
                case 3:
                case 4:
                    //받은값에 따라 햄버거 추가
                    kiosk.showSelectedBurgerMenu(input-1);
                    break;
                default:
                    break;
            }
            if(input == 0) //0을 입력받으면 종료
                break;
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private static int getValidInput(Scanner sc){
        while(!sc.hasNextInt())
        {
            sc.next();
            System.out.println("잘못 입력하셨습니다. 숫자를 입력하세요.");
        }
        return sc.nextInt();
    }
}
