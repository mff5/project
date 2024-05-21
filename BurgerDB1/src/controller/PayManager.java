package controller;

import model.MyBurgerVO;
import model.MySideVO;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class PayManager {
    public void pay() {

        ArrayList<MyBurgerVO> myBurgers = BurgerDAO.readMyBurgers();
        ArrayList<MySideVO> mySides = SideDAO.readMySides();

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.getYear() + "년 " + now.getMonthValue() + "월 " + now.getDayOfMonth() + "일 " + now.getHour() + "시 "
                + now.getMinute() + "분 " + now.getSecond() + "초");


        CartManager cm = new CartManager();
        cm.showCart();

        if (!myBurgers.isEmpty() || !mySides.isEmpty()) {
            int total = 0;
            try (Socket client = new Socket("localhost", 8000);
                 PrintWriter pw = new PrintWriter(new OutputStreamWriter(client.getOutputStream()))) {

                pw.println(now);

                if (!myBurgers.isEmpty()) {
                    pw.println("버거");
                    for (MyBurgerVO mb : myBurgers) {
                        pw.println(mb.toString2());
                        total += mb.getPrice();
                    }
                }

                if (!mySides.isEmpty()) {
                    pw.println("사이드");
                    for (MySideVO ms : mySides) {
                        pw.println(ms.toString2());
                        total += ms.getPrice();
                    }
                }

                pw.println(total + "원");
                pw.flush();


                PayDAO pd = new PayDAO();
                pd.pay(total);

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("결제중 오류 발생");
            }
        }

    }
}
