package controller;



import model.CartVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class CartDAO {
    public void deleteCart() {
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt1 = con.prepareStatement("truncate table myBurgers");
             PreparedStatement pstmt2 = con.prepareStatement("truncate table mySides");
             PreparedStatement pstmt3 = con.prepareStatement("drop sequence myBurgersNum");
             PreparedStatement pstmt4 = con.prepareStatement("create sequence myBurgersNum start with 1 increment by 1");
             PreparedStatement pstmt5 = con.prepareStatement("drop sequence mySidesNum");
             PreparedStatement pstmt6 = con.prepareStatement("create sequence mySidesNum start with 1 increment by 1")) {

            pstmt1.executeUpdate();
            pstmt2.executeUpdate();
            pstmt3.executeUpdate();
            pstmt4.executeUpdate();
            pstmt5.executeUpdate();
            pstmt6.executeUpdate();

            System.out.println("카트 비우기가 완료되었습니다.");

        } catch (Exception e) {
            System.out.println("카트 비우기중 오류 발생");
        }
    }
    public static ArrayList<CartVO> readCart() {
        ArrayList<CartVO> carts = new ArrayList<>();
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement("SELECT * FROM myBurgers ORDER BY orderNum");
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int cartCode = Integer.parseInt(rs.getString("CART_CODE"));
                String productName = rs.getString("PRODUCT_NAME");
                int price = Integer.parseInt(rs.getString("PRICE").trim().replaceAll("[^\\d]", ""));


                CartVO cart = new CartVO(cartCode, productName, price);
                carts.add(cart);
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("카트 버거목록 읽기 예외 발생");
        }
        return carts;
    }
}
