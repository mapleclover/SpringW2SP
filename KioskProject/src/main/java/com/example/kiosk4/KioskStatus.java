package com.example.kiosk4;

public enum KioskStatus {
    CLOSE,
    INITIAL,
    MAIN_MENU,
    //메인메뉴에서 접근가능한 메뉴는 이 아래 기입
    BURGERS_SELECTION,
    DRINKS_SELECTION,
    DESSERTS_SELECTION,
    ORDER_CHECK_OUT,
}
