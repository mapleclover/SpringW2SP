package com.example.kiosk6;

public enum KioskStatus {
    MAIN_MENU,
    //순서변경 금지
    BURGERS_SELECTION,
    DRINKS_SELECTION,
    DESSERTS_SELECTION,
    ORDER_MENU,
    CANCEL_ORDER,
    //순서변경 금지
    CONFIRM_ITEM,
    DISCOUNT_INFO,
    CLOSE,
    INITIAL
}
