package controller;


import java.util.ArrayList;


public class CartManager {
    public void showCart()  {
        int total = 0;

        ArrayList<MyBurgerVO> myBurgers = BurgerDAO.readMyBurgers();
        ArrayList<MySideVO> mySides = SideDAO.readMySides();


        if (myBurgers.isEmpty()) {
            System.out.println("선택하신 버거가 없습니다.");
        } else {
            System.out.println("버거");
            for (MyBurgerVO mb : myBurgers) {
                System.out.println(mb);
                total += mb.getPrice();
            }
        }

        if (mySides.isEmpty()) {
            System.out.println("추가하신 사이드가 없습니다.");
        } else {
            System.out.println("사이드");
            for (MySideVO ms : mySides) {
                System.out.println(ms);
                total += ms.getPrice();
            }
        }
        System.out.println(total + "원");
    }
    public void clearCart() {
        CartDAO cd = new CartDAO();
        cd.deleteCart();
    }
}
