package com.example.kiosk;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean run = true;
        int input;

        //키오스크 객채화 및 초기화
        Kiosk kiosk = new Kiosk();
        kiosk.initialize();

        Scanner sc = new Scanner(System.in);

        while(run)
        {
            kiosk.showBurgerMenu(); //메뉴 보여주기

            while (!sc.hasNextInt()) {
                sc.next();
                System.out.println("잘못 입력하셨습니다.");
            }

            input  = sc.nextInt();

            switch (input) {
                case 1:
                case 2:
                case 3:
                case 4:
                    //받은값에 따라 햄버거 추가
                    break;
                case 0: //0을 받으면 프로그램 종료
                    run = false;
                default:
                    break;
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }
}
