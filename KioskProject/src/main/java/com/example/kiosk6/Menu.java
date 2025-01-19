package com.example.kiosk6;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Menu<T extends MenuItem> {
    private List<T> items = new ArrayList<>();

    public void setMenuItem(T item){
        if(item instanceof ShoppingCartItem){
            //쇼핑 아이템이고 이미 같은 상품이 존재한다면 수량 더하기
            for(T cartItem : items){
                if(item.getName().equals(cartItem.getName())){
                    ((ShoppingCartItem)cartItem).addCount();
                    return;
                }
            }
        }
        items.add(item);
    }

    public List<T> getMenuItems() {
        return new ArrayList<>(items);
    }
    public List<T> getMenuItemsByType(MenuType menuType) {
        return items.stream().filter(item -> item.getMenuType() == menuType).collect(Collectors.toList());
    }

    public void deleteMenuItemsByName(String string){
        items = items.stream().filter(item -> !item.getName().toLowerCase().contains(string.toLowerCase())).collect(Collectors.toList());
    }
    public void emptyItems(){
        items.clear();
    }
}
