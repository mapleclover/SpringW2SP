package com.example.kiosk6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class Kiosk {
    Logger logger = LoggerFactory.getLogger(Kiosk.class);

    //메뉴 리스트
    private Menu<MenuItem> menu = new Menu<>();
    //장바구니 리스트
    private Menu<ShoppingCartItem> shoppingCart = new Menu<>();
    //키오스크 상태
    private KioskStatus kioskStatus = KioskStatus.INITIAL;
    //장바구니에 넣기전에 담아둘 변수
    private MenuItem selectedMenu;
    private float totalPrice;

    public Kiosk(MenuItem... menuItems) {
        for (MenuItem menuItem : menuItems) {
            menu.setMenuItem(menuItem);
        }
    }

    //키오스크 로직
    public void start() {
        kioskStatus = KioskStatus.MAIN_MENU;

        Scanner sc = new Scanner(System.in);
        int input;

        while (kioskStatus != KioskStatus.CLOSE) {
            showMenu();

            handleUserInput(sc);
        }
    }

    //출력 페이지를 정해주기
    public void showMenu() {
        switch (kioskStatus) {
            case MAIN_MENU:
                showMainMenu();
                break;
            case BURGERS_SELECTION:
                showBurgerMenu();
                break;
            case DRINKS_SELECTION:
                showDrinkMenu();
                break;
            case DESSERTS_SELECTION:
                showDessertMenu();
                break;
            case ORDER_MENU:
                showOrderMenu();
                break;
            case CANCEL_ORDER:
                showItemToRemove();
                break;
            case CONFIRM_ITEM:
                showConfirmItemMenu();
                break;
            case DISCOUNT_INFO:
                showDiscountMenu();
            case INITIAL:
            case CLOSE:
            default:
                break;
        }
    }

    //메인 메뉴를 출력하는 키오스크
    public void showMainMenu() {
        logger.info("[  MAIN MENU  ]");
        logger.info("1. Burgers");
        logger.info("2. Drinks");
        logger.info("3. Desserts");
        if(!shoppingCart.getMenuItems().isEmpty()){
            logger.info("4. Orders");
            logger.info("5. Cancel");
        }
        logger.info("0. Exit");
    }

    //버거 메뉴를 출력하는 키오스크
    public void showBurgerMenu() {
        logger.info("[  BURGERS MENU  ]");

        List<MenuItem> burgerMenus = menu.getMenuItemsByType(MenuType.BURGERS); //버거 메뉴 전체 가져오기
        //버거 메뉴가 비어있다면 메인 메뉴로 되돌리기
        if (burgerMenus.isEmpty()) {
            logger.info("버거 메뉴가 준비중입니다. 메인 메뉴로 돌아갑니다.");
            forceReturnToMainMenu();
            return;
        }

        MenuItem burger; //버거 하나씩 담을 변수 선언

        for (int i = 0; i < burgerMenus.size(); i++) {
            burger = burgerMenus.get(i);
            String temp = String.format("%d. %-15s | W %.1f | %s", i + 1, burger.getName(), burger.getPrice(), burger.getDescription());
            logger.info(temp);
        }

        logger.info("0. 뒤로가기");
    }

    //음료 메뉴를 출력하는 키오스크
    public void showDrinkMenu() {
        logger.info("[  DRINKS MENU  ]");

        List<MenuItem> drinkMenus = menu.getMenuItemsByType(MenuType.DRINKS); //버거 메뉴 전체 가져오기
        //음료 메뉴가 비어있다면 메인 메뉴로 되돌리기
        if (drinkMenus.isEmpty()) {
            logger.info("음료 메뉴가 준비중입니다. 메인 메뉴로 돌아갑니다.");
            forceReturnToMainMenu();
            return;
        }
        MenuItem drink; //버거 하나씩 담을 변수 선언

        for (int i = 0; i < drinkMenus.size(); i++) {
            drink = drinkMenus.get(i);
            String temp = String.format("%d. %-15s | W %.1f | %s", i + 1, drink.getName(), drink.getPrice(), drink.getDescription());
            logger.info(temp);
        }

        logger.info("0. 뒤로가기");
    }

    //디저트 메뉴를 출력하는 키오스크
    public void showDessertMenu() {
        logger.info("[  DESSERTS MENU  ]");

        List<MenuItem> dessertMenus = menu.getMenuItemsByType(MenuType.DESSERTS); //버거 메뉴 전체 가져오기
        //디저트 메뉴가 비어있다면 메인 메뉴로 되돌리기
        if (dessertMenus.isEmpty()) {
            logger.info("디저트 메뉴가 준비중입니다. 메인 메뉴로 돌아갑니다.");
            forceReturnToMainMenu();
            return;
        }
        MenuItem dessert; //버거 하나씩 담을 변수 선언

        for (int i = 0; i < dessertMenus.size(); i++) {
            dessert = dessertMenus.get(i);
            String temp = String.format("%d. %-15s | W %.1f | %s", i + 1, dessert.getName(), dessert.getPrice(), dessert.getDescription());
            logger.info(temp);
        }

        logger.info("0. 뒤로가기");
    }

    //선택한 메뉴를 장바구니에 담을 지 결정하는 메뉴
    public void showConfirmItemMenu(){
        logger.info("위 메뉴를 장바구니에 추가하시겠습니까?");
        logger.info("1. 확인       2. 취소");
    }

    //할인을 선택하는 메뉴를 보여줌
    public void showDiscountMenu(){
        logger.info("할인 정보를 입력해주세요.");
        logger.info("1. 국가유공자 : 10%");
        logger.info("2. 군인      : 5%");
        logger.info("3. 학생      : 3%");
        logger.info("4. 일반      : 0%");
    }

    //장바구니에서 물품 빼기
    public void showItemToRemove(){
        logger.info("[  Remove Orders  ]");
        List<ShoppingCartItem> cartItems = shoppingCart.getMenuItems();
        cartItems.forEach(cartItem -> {
            totalPrice += cartItem.getPrice() * cartItem.getCount();
            String temp = String.format("%-15s | W %.1f | 수량 : %d", cartItem.getName(), cartItem.getPrice(), cartItem.getCount());
            logger.info(temp);
        });
        logger.info("장바구니에서 제거할 품목 또는 품목명에 포함된 키워드를 입력해주세요");
    }

    //장바구니를 보여주는 키오스크
    public void showOrderMenu(){
        totalPrice = 0f;
        logger.info("[  Orders  ]");
        List<ShoppingCartItem> cartItems = shoppingCart.getMenuItems();
        cartItems.forEach(cartItem -> {
            totalPrice += cartItem.getPrice() * cartItem.getCount();
            String temp = String.format("%-15s | W %.1f | 수량 : %d | %s", cartItem.getName(), cartItem.getPrice(), cartItem.getCount() ,cartItem.getDescription());
            logger.info(temp);
        });

        logger.info("[  Total  ]");
        String price = String.format("W %.1f\n", totalPrice);
        logger.info(price);
        logger.info("1. 주문        2. 메뉴판");
    }

    //선택한 종류의 메뉴가 비어있을 경우 강제로 메인메뉴로 복귀
    private void forceReturnToMainMenu() {
        kioskStatus = KioskStatus.MAIN_MENU;
        showMenu();
    }

    public void handleUserInput(Scanner sc) {
        String userInputString;
        int userInput = 0;

        userInputString = sc.nextLine();

        if (kioskStatus != KioskStatus.CANCEL_ORDER) {
            try{
                userInput = Integer.parseInt(userInputString);
            } catch(NumberFormatException e){
                logger.info("잘못 입력하셨습니다. 숫자를 입력하세요.");
                return;
            }
        }

        switch (kioskStatus) {
            case MAIN_MENU:
                moveToSelectedMenu(userInput);
                break;
            case BURGERS_SELECTION:
                showSelectedBurgerMenu(userInput);
                break;
            case ORDER_MENU:
                decideOrder(userInput);
                break;
            case CANCEL_ORDER:
                deletedCartItemByName(userInputString);
                break;
            case CONFIRM_ITEM:
                decideItemToCart(userInput);
                break;
            case DISCOUNT_INFO:
                decideDiscount(userInput);
                break;
            default:
                break;
        }
    }

    //메뉴를 이동하는 로직
    public void moveToSelectedMenu(int userInput) {
        if(userInput == 0){
            kioskStatus = KioskStatus.CLOSE;
        }

        //쇼핑카트가 비어있으면 디저트메뉴까지만 접근가능, 비어있지않으면 오더 취소 메뉴까지 접근가능
        KioskStatus allowedKioskStatus = shoppingCart.getMenuItems().isEmpty() ? KioskStatus.DESSERTS_SELECTION : KioskStatus.CANCEL_ORDER;

        if (userInput >= KioskStatus.BURGERS_SELECTION.ordinal()
                && userInput <= allowedKioskStatus.ordinal()) {
            kioskStatus = KioskStatus.values()[userInput];
        }
        else{
            logger.info("없는 메뉴를 입력했습니다.");
        }
    }

    //선택한 버거 메뉴를 알림하는 키오스크
    public void showSelectedBurgerMenu(int userInput) {
        int convertInput = userInput - 1;
        if (menu.getMenuItemsByType(MenuType.BURGERS).size() <= convertInput) {
            logger.info("존재하지 않는 메뉴입니다.");
            return;
        }
        selectedMenu = menu.getMenuItemsByType(MenuType.BURGERS).get(convertInput);
        String temp = String.format("선택한 메뉴 : %s 는 %s 입니다. 가격은 W %.1f 입니다.\n", selectedMenu.getName(), selectedMenu.getDescription(), selectedMenu.getPrice());
        logger.info(temp);
        kioskStatus = KioskStatus.CONFIRM_ITEM;
    }

    //입력받은 String 값을 포함하고 있는 메뉴를 삭제하는 기능
    public void deletedCartItemByName(String userInputString){
        shoppingCart.deleteMenuItemsByName(userInputString);
        String temp = String.format("장바구니에서 %s 가 포함되어 있는 품목이 삭제되었습니다.\n", userInputString);
        logger.info(temp);
        kioskStatus = KioskStatus.MAIN_MENU;
    }

    //장바구니 추가, 취소 결정
    public void decideItemToCart(int userInput){
        if(userInput == 1) {
            shoppingCart.setMenuItem(new ShoppingCartItem(selectedMenu.menuType, selectedMenu.getName(), selectedMenu.getPrice(), selectedMenu.getDescription()));
            kioskStatus = KioskStatus.MAIN_MENU;
            String temp = String.format("%s 이 장바구니에 추가되었습니다.\n", selectedMenu.getName());
            logger.info(temp);
        } else {
            kioskStatus = KioskStatus.BURGERS_SELECTION;
            logger.info("취소되었습니다.");
        }
    }

    //구매가 결정되면 할인 선택 화면으로 이동
    public void decideOrder(int userInput){
        if(userInput == 1) {
            kioskStatus = KioskStatus.DISCOUNT_INFO;
        } else {
            logger.info("메인 메뉴로 돌아갑니다.");
            kioskStatus = KioskStatus.MAIN_MENU;
        }
    }

    //할인율 선택하고 결제하고 장바구니를 비움
    public void decideDiscount(int userInput){
        DiscountType discountType;
        if(userInput >= 1 && userInput <= DiscountType.values().length){
            discountType = DiscountType.values()[userInput-1];
        } else{
            discountType = DiscountType.GENERAL;
        }

        float discount = switch (discountType) {
            case VETERAN -> 0.9f;
            case SOLDIER -> 0.95f;
            case STUDENT -> 0.97f;
            default -> 1f;
        };

        String temp = String.format("주문이 완료 되었습니다. 금액은 %.3f 입니다.", totalPrice * discount);
        logger.info(temp);
        totalPrice = 0f;
        shoppingCart.emptyItems();
        kioskStatus = KioskStatus.MAIN_MENU;
    }
}