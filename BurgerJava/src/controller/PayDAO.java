package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

public class PayDAO {
    public void pay(int total)   {
        try(Connection con = DBUtil.getConnection();
            PreparedStatement pstmt1 = con.prepareStatement("INSERT INTO receipt VALUES (lpad(receiptNum.nextval,5,'0'),?,trim(to_char(?,'L999,999,999')))");
            PreparedStatement pstmt2 = con.prepareStatement("truncate table myBurgers");
            PreparedStatement pstmt3 = con.prepareStatement("truncate table mySides");
            PreparedStatement pstmt4 = con.prepareStatement("drop sequence myBurgersNum");
            PreparedStatement pstmt5 = con.prepareStatement("create sequence myBurgersNum start with 1 increment by 1");
            PreparedStatement pstmt6 = con.prepareStatement("drop sequence mySidesNum");
            PreparedStatement pstmt7 = con.prepareStatement("create sequence mySidesNum start with 1 increment by 1")) {

            LocalDateTime now = LocalDateTime.now();

            pstmt1.setString(1, now.toString());
            pstmt1.setInt(2, total);

            pstmt1.executeUpdate();
            pstmt2.executeUpdate();
            pstmt3.executeUpdate();
            pstmt4.executeUpdate();
            pstmt5.executeUpdate();
            pstmt6.executeUpdate();
            pstmt7.executeUpdate();
            System.out.println("결제가 완료되었습니다.");

        } catch (Exception e)   {
            System.out.println("결제중 오류 발생");
        }
    }
}
