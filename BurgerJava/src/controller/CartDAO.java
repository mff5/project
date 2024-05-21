package controller;



import java.sql.Connection;
import java.sql.PreparedStatement;



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
}
