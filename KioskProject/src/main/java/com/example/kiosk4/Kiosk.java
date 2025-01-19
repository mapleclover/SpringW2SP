package com.example.kiosk4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class Kiosk {
    Logger logger = LoggerFactory.getLogger(Kiosk.class);

    Menu menu = new Menu();
    KioskStatus kioskStatus = KioskStatus.INITIAL;

    public Kiosk (MenuItem... menuItems) {
        for (MenuItem menuItem : menuItems) {
            menu.addMenuItem(menuItem);
        }
    }

    //키오스크 로직
    public void start()
    {
        kioskStatus = KioskStatus.MAIN_MENU;

        Scanner sc = new Scanner(System.in);
        int input;

        while(kioskStatus != KioskStatus.CLOSE)
        {
            showMenu();

            if (!sc.hasNextInt()) {
                sc.next();
                logger.info("잘못 입력하셨습니다. 숫자를 입력하세요.");
                continue;
            }
            input = sc.nextInt();

            handleUserInput(input);
        }
    }

    //출력 페이지를 정해주기
    public void showMenu(){
        switch(kioskStatus){
            case MAIN_MENU :
                showMainMenu();
                break;
            case BURGERS_SELECTION :
                showBurgerMenu();
                break;
            case DRINKS_SELECTION :
                showDrinkMenu();
                break;
            case DESSERTS_SELECTION :
                showDessertMenu();
                break;
            case ORDER_CHECK_OUT :
            case INITIAL :
            case CLOSE :
            default :
                break;
        }
    }
    //메인 메뉴를 출력하는 키오스크
    public void showMainMenu() {
        logger.info("[  MAIN MENU  ]");
        logger.info("1. Burgers");
        logger.info("2. Drinks");
        logger.info("3. Desserts");
        logger.info("0. 종료");
    }

    //버거 메뉴를 출력하는 키오스크
    public void showBurgerMenu() {
        logger.info("[  BURGERS MENU  ]");

        List<MenuItem> burgerMenus = menu.getBurgers(); //버거 메뉴 전체 가져오기
        //버거 메뉴가 비어있다면 메인 메뉴로 되돌리기
        if(burgerMenus.isEmpty()) {
            logger.info("버거 메뉴가 준비중입니다. 메인 메뉴로 돌아갑니다.");
            forceReturnToMainMenu();
            return;
        }

        MenuItem burger; //버거 하나씩 담을 변수 선언

        for (int i = 0; i < burgerMenus.size(); i++) {
            burger = burgerMenus.get(i);
            String temp = String.format("%d. %-15s | W %.1f | %s" , i+1, burger.getName(), burger.getPrice(), burger.getDescription());
            logger.info(temp);
        }

        logger.info("0. 뒤로가기");
    }
    //음료 메뉴를 출력하는 키오스크
    public void showDrinkMenu() {
        logger.info("[  DRINKS MENU  ]");

        List<MenuItem> drinkMenus = menu.getDrinks(); //버거 메뉴 전체 가져오기
        //음료 메뉴가 비어있다면 메인 메뉴로 되돌리기
        if(drinkMenus.isEmpty()) {
            logger.info("음료 메뉴가 준비중입니다. 메인 메뉴로 돌아갑니다.");
            forceReturnToMainMenu();
            return;
        }
        MenuItem drink; //버거 하나씩 담을 변수 선언

        for (int i = 0; i < drinkMenus.size(); i++) {
            drink = drinkMenus.get(i);
            String temp = String.format("%d. %-15s | W %.1f | %s" , i+1, drink.getName(), drink.getPrice(), drink.getDescription());
            logger.info(temp);
        }

        logger.info("0. 뒤로가기");
    }
    //디저트 메뉴를 출력하는 키오스크
    public void showDessertMenu() {
        logger.info("[  DESSERTS MENU  ]");

        List<MenuItem> dessertMenus = menu.getDesserts(); //버거 메뉴 전체 가져오기
        //디저트 메뉴가 비어있다면 메인 메뉴로 되돌리기
        if(dessertMenus.isEmpty()) {
            logger.info("디저트 메뉴가 준비중입니다. 메인 메뉴로 돌아갑니다.");
            forceReturnToMainMenu();
            return;
        }
        MenuItem dessert; //버거 하나씩 담을 변수 선언

        for (int i = 0; i < dessertMenus.size(); i++) {
            dessert = dessertMenus.get(i);
            String temp = String.format("%d. %-15s | W %.1f | %s" , i+1, dessert.getName(), dessert.getPrice(), dessert.getDescription());
            logger.info(temp);
        }

        logger.info("0. 뒤로가기");
    }

    //선택한 종류의 메뉴가 비어있을 경우 강제로 메인메뉴로 복귀
    private void forceReturnToMainMenu(){
        kioskStatus = KioskStatus.MAIN_MENU;
        showMenu();
    }

    public void handleUserInput(int userInput) {
        if(userInput < 0) {
            logger.info("잘못된 숫자를 입력했습니다.");
            return;
        }
        if(userInput == 0){
            if(kioskStatus == KioskStatus.MAIN_MENU) {
                kioskStatus = KioskStatus.CLOSE;
            }else{
                kioskStatus = KioskStatus.MAIN_MENU;
            }
        } else {
            switch (kioskStatus) {
                case MAIN_MENU:
                    moveToSelectedMenu(userInput);
                    break;
                case BURGERS_SELECTION :
                    showSelectedBurgerMenu(userInput-1);
                    break;
                default:
                    break;
            }
        }
    }

    //메뉴를 이동하는 로직
    public void moveToSelectedMenu(int userInput){
        int INPUT_TO_ENUM = 2; //유저 인풋과 ENUM의 메뉴 표시의 차이값 만큼 더함.
        int convertedInput = userInput + INPUT_TO_ENUM;
        if(convertedInput >= KioskStatus.BURGERS_SELECTION.ordinal()
                && userInput <= KioskStatus.DESSERTS_SELECTION.ordinal()) {
            kioskStatus = KioskStatus.values()[convertedInput];
        }
        else {
            logger.info("없는 메뉴를 입력했습니다.");
        }
    }

    //선택한 버거 메뉴를 알림하는 키오스크
    public void showSelectedBurgerMenu(int index){
        if(menu.getBurgers().size() <= index){
            logger.info("존재하지 않는 메뉴입니다.");
            return;
        }
        MenuItem burger = menu.getBurgers().get(index);
        String temp = String.format("선택한 메뉴 : %s 는 %s 입니다. 가격은 W %.1f 입니다.\n", burger.getName(), burger.getDescription(), burger.getPrice());
        logger.info(temp);
        //메뉴를 선택한 이후에 메인 메뉴로 복귀
        kioskStatus = KioskStatus.MAIN_MENU;
    }


}


